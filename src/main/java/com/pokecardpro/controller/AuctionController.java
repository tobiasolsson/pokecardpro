package com.pokecardpro.controller;

import com.pokecardpro.models.Auction;
import com.pokecardpro.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class AuctionController {
    @Autowired
    AuctionService auctionService;

    @PostMapping("auction")
    public Auction createAuction(@RequestBody Auction auction) {
        // get current timestamp
        LocalDateTime currentTime = LocalDateTime.now();
        // and set endDate to current time + 7 days
        LocalDateTime endDate = currentTime.plusDays(7);
        // set to correct type
        Timestamp timestamp = Timestamp.valueOf(endDate);
        // set endDate in object
        auction.setEndDate(timestamp);

        return auctionService.createAuction(auction);
    }

    @GetMapping("auction")
    public List<Auction> getAllAuctions() {
        return auctionService.getAllAuctions();
    }

    @GetMapping("auction/active")
    public List<Auction> getAllActiveAuctions() {
        return auctionService.getAllActiveAuctions();
    }

    @GetMapping("auction/{id}")
    public Auction getAuctionById(@PathVariable String id) {
        return auctionService.getAuctionById(id);
    }
}
