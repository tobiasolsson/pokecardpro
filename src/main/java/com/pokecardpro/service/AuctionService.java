package com.pokecardpro.service;

import com.pokecardpro.dto.AuctionDTO;
import com.pokecardpro.dto.BidsDTO;
import com.pokecardpro.dto.UserDTO;
import com.pokecardpro.models.Auction;
import com.pokecardpro.models.Bids;
import com.pokecardpro.models.Card;
import com.pokecardpro.models.User;
import com.pokecardpro.repository.AuctionRepository;
import com.pokecardpro.repository.CardRepository;
import com.pokecardpro.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AuctionService {
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final BidService bidService;

    public AuctionService(AuctionRepository auctionRepository, UserRepository userRepository, CardRepository cardRepository,
                          BidService bidService) {
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.bidService = bidService;
    }

    //@PreAuthorize("@authenticationService.getHasAccess(#auction.userId.id)")
    public ResponseEntity<String> createAuction(Auction auction) {
        // get current timestamp
        LocalDateTime currentTime = LocalDateTime.now();
        // set dates in object
        auction.setStartDate(Timestamp.valueOf(currentTime));
        auction.setEndDate(Timestamp.valueOf(currentTime.plusDays(7)));

        try {
            // get actual user from userId in auction and set it in auction
            //String userId = Integer.toString(auction.getUserId().getId());

            // TODO: Break out to own function, used multiple places
            // get the userId from the securitycontext, this way we don't need to send and deal with user id on the frontend
            String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            User userAuth = userRepository.findByEmail(authenticatedUserEmail).orElseThrow(
                    () -> new NoSuchElementException("Something went wrong trying to fetch user object"));
            String userId = String.valueOf(userAuth.getId());

            User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found with id " + userId));
            auction.setUserId(user);

            // get actual car from cardId and set in auction
            String cardId = Integer.toString(auction.getCardId().getId());
            Card card = cardRepository.findById(cardId).orElseThrow(() -> new NoSuchElementException("Card not found with id " + cardId));
            auction.setCardId(card);


            return ResponseEntity.ok("Auction created with id: " + auctionRepository.save(auction).getId());
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // TODO: Return DTO
    public ResponseEntity<List<Auction>> getAllAuctions() {
        List<Auction> auctions = auctionRepository.findAll();
        if (auctions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(auctions);
    }

    // TODO: Return DTO
    public ResponseEntity<List<Auction>> getAllActiveAuctions() {
        List<Auction> activeAuctions = auctionRepository.findAllAuctionsByStatusTrue();
        if (activeAuctions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(activeAuctions);
    }

    public ResponseEntity<AuctionDTO> getAuctionById(String id) {
        try {
           Auction auction = auctionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Auction not found with id: " + id));
            UserDTO userDTO = new UserDTO(auction.getUserId().getFirstName(), auction.getUserId().getLastName(),
                                          auction.getUserId().getEmail(), auction.getUserId().getPhone(), auction.getUserId().getStreet(),
                                          auction.getUserId().getStreetNr(), auction.getUserId().getCity(), auction.getUserId().getZipCode());
            AuctionDTO auctionDTO = new AuctionDTO(userDTO, auction.getTitle(), auction.getDescription(),
                                                   auction.isStatus(), auction.getBuyNow(), auction.getReservedPrice(),
                                                   auction.getStartDate(), auction.getEndDate(), auction.getPickUp(),
                                                   auction.getShipping(), auction.getShippingCost(), auction.getEndBid());

            return ResponseEntity.ok(auctionDTO);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //return auction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TODO: Return DTO
    public ResponseEntity<List<Auction>> getAllOldAuctions() {
        List<Auction> oldAuctions = auctionRepository.findAllAuctionsByStatusFalse();
        if (oldAuctions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oldAuctions);
    }

    // TODO: Return DTO
    public ResponseEntity<List<Auction>> getAllAuctionsOfPokemon(String pokemonName) {
        Predicate<Auction> predicate =
                auction -> auction.getCardId().getPokemon().getName().equalsIgnoreCase(pokemonName);

        List<Auction> pokemonAuctions = auctionRepository.findAllAuctionsByStatusTrue().stream()
                .filter(predicate)
                .collect(Collectors.toList());

        if (pokemonAuctions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pokemonAuctions);
    }

    public ResponseEntity<Integer> getHighestBidInAuction(String id) {
        try {
            List<BidsDTO> bids = (List<BidsDTO>) bidService.getBidsByAuction(id);

            BidsDTO highestBid = bids.stream()
                    .max(Comparator.comparing(BidsDTO::amount))
                    .orElse(null);

            return ResponseEntity.ok(bids.isEmpty() ? 0 : highestBid.amount());
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(0);
        }
    }
}
