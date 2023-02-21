package com.pokecardpro.controller;
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

    @PostMapping("watchlist")
    public Watchlist saveAuctionToWatchlist(@RequestBody Watchlist watchlist) {
        return watchlistService.saveAuction(watchlist);
    }

    @GetMapping("watchlist")
    public List<Watchlist> getAllAuctionsInWatchlist() {
        return watchlistService.getAllAuctionsInWatchlist();
    }

    @DeleteMapping("delete/{id}")
    public String deleteAuctionFromWatchlist(@PathVariable String id) {
        return watchlistService.deleteAuction(id);
    }
}
