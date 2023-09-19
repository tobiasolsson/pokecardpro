package com.pokecardpro.service;

import com.pokecardpro.models.Auction;
import com.pokecardpro.models.Card;
import com.pokecardpro.models.User;
import com.pokecardpro.repository.AuctionRepository;
import com.pokecardpro.repository.CardRepository;
import com.pokecardpro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AuctionService {
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    public AuctionService(AuctionRepository auctionRepository, UserRepository userRepository, CardRepository cardRepository) {
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    @PreAuthorize("@auctionService.getHasAccess(#auction.userId.id)")
    public ResponseEntity<String> createAuction(Auction auction) {
        // get current timestamp
        LocalDateTime currentTime = LocalDateTime.now();
        // set dates in object
        auction.setStartDate(Timestamp.valueOf(currentTime));
        auction.setEndDate(Timestamp.valueOf(currentTime.plusDays(7)));

        try {
            // get actual user from userId in auction and set it in auction
            String userId = Integer.toString(auction.getUserId().getId());
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

    public ResponseEntity<List<Auction>> getAllAuctions() {
        List<Auction> auctions = auctionRepository.findAll();
        if (auctions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(auctions);
    }

    public ResponseEntity<List<Auction>> getAllActiveAuctions() {
        List<Auction> activeAuctions = auctionRepository.findAllAuctionsByStatusTrue();
        if (activeAuctions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(activeAuctions);
    }

    public ResponseEntity<Auction> getAuctionById(String id) {
        Optional<Auction> auction = auctionRepository.findById(id);
        return auction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Auction>> getAllOldAuctions() {
        List<Auction> oldAuctions = auctionRepository.findAllAuctionsByStatusFalse();
        if (oldAuctions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oldAuctions);
    }

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

}
