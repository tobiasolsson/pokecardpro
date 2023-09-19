package com.pokecardpro.service;

import com.pokecardpro.models.Auction;
import com.pokecardpro.models.User;
import com.pokecardpro.models.Watchlist;
import com.pokecardpro.repository.AuctionRepository;
import com.pokecardpro.repository.UserRepository;
import com.pokecardpro.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WatchlistService {
    private final WatchlistRepository watchlistRepository;
    private final UserRepository userRepository;
    private final AuctionRepository auctionRepository;

    public WatchlistService(WatchlistRepository watchlistRepository, UserRepository userRepository, AuctionRepository auctionRepository) {
        this.watchlistRepository = watchlistRepository;
        this.userRepository = userRepository;
        this.auctionRepository = auctionRepository;
    }


    @PreAuthorize("@authenticationService.getHasAccess(#userId)")
    public ResponseEntity<String> saveAuctionToWatchlist(String userId, String auctionId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));

            Auction auction = auctionRepository.findById(auctionId).orElseThrow(() -> new NoSuchElementException("Auction not found with id: " + auctionId));

            if (user.getWatchlist() == null || user.getWatchlist().getAuctions().isEmpty()) {
                Watchlist watchlist = new Watchlist(new ArrayList<>(), user);
                user.setWatchlist(watchlist);
            }

            Watchlist watchlist = user.getWatchlist();

            watchlist.getAuctions().add(auction);
            return ResponseEntity.ok("Saved auction: " + auction.getId() + " to watchlist");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong, error message: " + e.getMessage());
        }
    }

    @PreAuthorize("@authenticationService.getHasAccess(#id)")
    public List<Auction> getUsersWatchlist(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("User not found with id: " + id)
        );
        Watchlist watchlist = user.getWatchlist();

        return watchlist.getAuctions();
    }

    public String deleteAuction(String id){watchlistRepository.deleteById(id);
        return "Auction has been deleted from your watchlist";
    }
}
