package com.pokecardpro.controller;

import com.pokecardpro.models.Auction;
import com.pokecardpro.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class AuctionController {
    @Autowired
    AuctionService auctionService;

    @PostMapping("auction")
    public Auction createAuction(@RequestBody Auction auction) {
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

    @GetMapping("auction/old")
    public List<Auction> getAllOldAuctions() {
        return auctionService.getAllOldAuctions();
    }

    @GetMapping("auction/{id}")
    public Auction getAuctionById(@PathVariable String id) {
        return auctionService.getAuctionById(id);
    }

    @GetMapping("auction/pokemon/{pokemonName}")
    public List<Auction> getAllAuctionsOfPokemon(@PathVariable String pokemonName) {
        return auctionService.getAllAuctionsOfPokemon(pokemonName);
    }
}
