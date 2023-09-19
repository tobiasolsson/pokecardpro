package com.pokecardpro.controller;
import com.pokecardpro.models.Auction;
import com.pokecardpro.models.Watchlist;
import com.pokecardpro.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class WatchlistController {

    @Autowired
    WatchlistService watchlistService;

   /* @PostMapping("watchlist/{userId}/{auctionId}")
    public Watchlist saveAuctionToWatchlist(@PathVariable String userId, @PathVariable String auctionId) {
        return watchlistService.saveAuctionToWatchlist(userId, auctionId);
    }*/

    @GetMapping("watchlist/{id}")
    public List<Auction> getAllAuctionsInWatchlist(@PathVariable String id) {
        return watchlistService.getUsersWatchlist(id);
    }

    @DeleteMapping("delete/{id}")
    public String deleteAuctionFromWatchlist(@PathVariable String id) {
        return watchlistService.deleteAuction(id);
    }
}
