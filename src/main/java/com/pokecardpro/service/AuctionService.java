package com.pokecardpro.service;

import com.pokecardpro.auth.AuthenticationService;
import com.pokecardpro.dto.AuctionDTO;
import com.pokecardpro.dto.BidsDTO;
import com.pokecardpro.models.Auction;
import com.pokecardpro.models.Card;
import com.pokecardpro.models.User;
import com.pokecardpro.repository.AuctionRepository;
import com.pokecardpro.repository.CardRepository;
import com.pokecardpro.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final AuthenticationService authenticationService;
    private final BidService bidService;

    public AuctionService(AuctionRepository auctionRepository, UserRepository userRepository, CardRepository cardRepository,
                          AuthenticationService authenticationService, BidService bidService) {
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.authenticationService = authenticationService;
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

            String userId = authenticationService.getUserId();

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

    public ResponseEntity<List<AuctionDTO>> getAllAuctions() {
        try {
            List<Auction> auctions = auctionRepository.findAll();
            return handleAuctionList(auctions);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<AuctionDTO>> getAllActiveAuctions() {
        try {
            List<Auction> auctions = auctionRepository.findAllAuctionsByStatusTrue();
            return handleAuctionList(auctions);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<AuctionDTO> getAuctionById(String id) {
        try {
           Auction auction = auctionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Auction not found with id: " + id));
           AuctionDTO auctionDTO = convertToAuctionDTO(auction);

            return ResponseEntity.ok(auctionDTO);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //return auction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<AuctionDTO>> getAllOldAuctions() {
        try {
            List<Auction> auctions = auctionRepository.findAllAuctionsByStatusFalse();
            return handleAuctionList(auctions);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // TODO: Return DTO, if it is needed when pokemon table is removed?
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

    private AuctionDTO convertToAuctionDTO(Auction auction) {
        return new AuctionDTO(auction.getId(),
                              auction.getTitle(),
                              auction.getDescription(),
                              auction.isStatus(),
                              auction.getBuyNow(),
                              auction.getReservedPrice(),
                              auction.getStartDate(),
                              auction.getEndDate(),
                              auction.getPickUp(),
                              auction.getShipping(),
                              auction.getShippingCost(),
                              auction.getEndBid());
    }

    private ResponseEntity<List<AuctionDTO>> handleAuctionList(List<Auction> auctions) {
        try {
            if (auctions.isEmpty()) throw new NoSuchElementException("No auctions found");
            List<AuctionDTO> auctionDTOList = auctions.stream()
                                                      .map(this::convertToAuctionDTO)
                                                      .toList();

            return ResponseEntity.ok(auctionDTOList);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
