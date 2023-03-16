package com.pokecardpro.service;

import com.pokecardpro.models.Auction;
import com.pokecardpro.models.User;
import com.pokecardpro.models.Watchlist;
import com.pokecardpro.repository.AuctionRepository;
import com.pokecardpro.repository.UserRepository;
import com.pokecardpro.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WatchlistService {

    @Autowired
    WatchlistRepository watchlistRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @PreAuthorize("@authenticationService.getHasAccess(#userId)")
    public Watchlist saveAuctionToWatchlist(String userId, String auctionId) {
        User user = userRepository.findById(userId).get();

        Auction auction = auctionRepository.findById(auctionId).get();

        if (user.getWatchlist() == null || user.getWatchlist().getAuctions().isEmpty()) {
            Watchlist watchlist = new Watchlist(new ArrayList<>(), user);
            user.setWatchlist(watchlist);
        }

        Watchlist watchlist = user.getWatchlist();

        watchlist.getAuctions().add(auction);
        return watchlistRepository.save(watchlist);
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
