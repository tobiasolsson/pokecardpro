package com.pokecardpro.controller;

import com.pokecardpro.dto.AuctionDTO;
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
    public ResponseEntity<List<AuctionDTO>> getAllAuctions() {
        return auctionService.getAllAuctions();
    }

    @GetMapping("auction/active")
    public ResponseEntity<List<AuctionDTO>> getAllActiveAuctions() {
        return auctionService.getAllActiveAuctions();
    }

    @GetMapping("auction/old")
    public ResponseEntity<List<AuctionDTO>> getAllOldAuctions() {
        return auctionService.getAllOldAuctions();
    }

    @CrossOrigin()
    @GetMapping("auction/{id}")
    public ResponseEntity<AuctionDTO> getAuctionById(@PathVariable String id) {
        return auctionService.getAuctionById(id);
    }

   // @GetMapping("auction/pokemon/{pokemonName}")
   // public ResponseEntity<List<Auction>> getAllAuctionsOfPokemon(@PathVariable String pokemonName) {
   //     return auctionService.getAllAuctionsOfPokemon(pokemonName);
   // }

    @GetMapping("auction-high-bids/{id}")
    public ResponseEntity<Integer> getHighestBidInAuction(@PathVariable String id) {
        return auctionService.getHighestBidInAuction(id);
    }
}
