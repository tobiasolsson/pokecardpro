package com.pokecardpro.repository;

import com.pokecardpro.models.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, String> {

    @Query
    List<Auction> findAllAuctionsByStatusTrue();

    @Query
    List<Auction> findAllAuctionsByStatusFalse();
}
