package com.pokecardpro.service;

import com.pokecardpro.models.Watchlist;
import com.pokecardpro.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    @Autowired
    WatchlistRepository watchlistRepository;

    public Watchlist saveAuction(Watchlist watchlist) {
        return watchlistRepository.save(watchlist);
    }

    public List<Watchlist> getAllAuctionsInWatchlist() {
        return watchlistRepository.findAll();
    }

    public String deleteAuction(String id){watchlistRepository.deleteById(id);
        return "Auction has been deleted from your watchlist";
    }
}
