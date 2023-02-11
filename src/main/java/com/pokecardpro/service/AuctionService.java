package com.pokecardpro.service;

import com.pokecardpro.models.Auction;
import com.pokecardpro.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {
    @Autowired
    AuctionRepository auctionRepository;

    public Auction createAuction(Auction auction) {
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
}
