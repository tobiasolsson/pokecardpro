package com.pokecardpro.service;
import com.pokecardpro.models.Auction;
import com.pokecardpro.models.Bids;
import com.pokecardpro.models.User;
import com.pokecardpro.repository.AuctionRepository;
import com.pokecardpro.repository.BidsRepository;
import com.pokecardpro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class BidService {

    @Autowired
    BidsRepository bidsRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<String> placeBid (Bids bids) {
        // get current timestamp
        LocalDateTime currentTime = LocalDateTime.now();
        // set dates in object
        bids.setCreated(Timestamp.valueOf(currentTime));

        // get the provided ids
        String auctionId = Integer.toString(bids.getAuction().getId());
        String userId = Integer.toString(bids.getUser().getId());

        // get user and auction
        Auction auction = auctionRepository.findById(auctionId).get();
        User user = userRepository.findById(userId).get();

        boolean auctionStatus = auction.isStatus();

        // set user and auction in bids
        bids.setAuction(auction);
        bids.setUser(user);


        if (auctionStatus) {
            bidsRepository.save(bids);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bid successfully placed!");
        } else {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Bid not placed!");
        }
    }
}
