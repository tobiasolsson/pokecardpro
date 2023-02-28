package com.pokecardpro.service;

import com.pokecardpro.models.Auction;
import com.pokecardpro.models.Card;
import com.pokecardpro.models.User;
import com.pokecardpro.repository.AuctionRepository;
import com.pokecardpro.repository.CardRepository;
import com.pokecardpro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AuctionService {
    @Autowired
    AuctionRepository auctionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;

    public Auction createAuction(Auction auction) {
        // get current timestamp
        LocalDateTime currentTime = LocalDateTime.now();
        // set dates in object
        auction.setStartDate(Timestamp.valueOf(currentTime));
        auction.setEndDate(Timestamp.valueOf(currentTime.plusDays(7)));

        // get actual user from userId in auction and set it in auction
        String userId = Integer.toString(auction.getUserId().getId());
        User user = userRepository.findById(userId).get();
        auction.setUserId(user);

        // get actual car from cardId and set in auction
        String cardId = Integer.toString(auction.getCardId().getId());
        Card card = cardRepository.findById(cardId).get();
        auction.setCardId(card);


        return auctionRepository.save(auction);
    }

    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    public List<Auction> getAllActiveAuctions() {
        return auctionRepository.findAllAuctionsByStatusTrue();
    }

    public Auction getAuctionById(String id) {
        return auctionRepository.findById(id).orElseThrow();
    }

    public List<Auction> getAllOldAuctions() {
        return auctionRepository.findAllAuctionsByStatusFalse();
    }

    public List<Auction> getAllAuctionsOfPokemon(String pokemonName) {
        // look into auction->card->pokemon and check if pokemon name == pokemonName argument
        Predicate<Auction> predicate =
                auction -> auction.getCardId().getPokemon().getName().equalsIgnoreCase(pokemonName);

        // loop through all active auctions, run above lambda function and create new list of auctions with same pokemon
        return getAllActiveAuctions().stream()
                                     .filter(predicate)
                                     .collect(Collectors.toList());
    }
}
