package com.pokecardpro.controller;

import com.pokecardpro.models.Auction;
import com.pokecardpro.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class AuctionController {
    @Autowired
    AuctionService auctionService;

    @PostMapping("auction")
    public ResponseEntity<String> createAuction(@RequestBody Auction auction) {
        return auctionService.createAuction(auction);
    }

    @GetMapping("auction")
    public ResponseEntity<List<Auction>> getAllAuctions() {
        return auctionService.getAllAuctions();
    }

    @GetMapping("auction/active")
    public ResponseEntity<List<Auction>> getAllActiveAuctions() {
        return auctionService.getAllActiveAuctions();
    }

    @GetMapping("auction/old")
    public ResponseEntity<List<Auction>> getAllOldAuctions() {
        return auctionService.getAllOldAuctions();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("auction/{id}")
    public ResponseEntity<Auction> getAuctionById(@PathVariable String id) {
        return auctionService.getAuctionById(id);
    }

    @GetMapping("auction/pokemon/{pokemonName}")
    public ResponseEntity<List<Auction>> getAllAuctionsOfPokemon(@PathVariable String pokemonName) {
        return auctionService.getAllAuctionsOfPokemon(pokemonName);
    }
}
