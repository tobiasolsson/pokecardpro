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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BidService {

    @Autowired
    BidsRepository bidsRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    UserRepository userRepository;

    //@PreAuthorize("@authenticationService.getHasAccess(#bids.user.id)") // behövs inte om vi hämtar user id från securitycontext?
    public ResponseEntity<String> placeBid (Bids bids) {
        // get current timestamp
        LocalDateTime currentTime = LocalDateTime.now();
        // set dates in object
        bids.setCreated(Timestamp.valueOf(currentTime));

        // get the ids of user and auctions
        String auctionId = Integer.toString(bids.getAuction().getId());

        // TODO: Break out to own function, used multiple places
        // get the userId from the securitycontext, this way we don't need to send and deal with user id on the frontend
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User userAuth = userRepository.findByEmail(authenticatedUserEmail).orElseThrow(
                () -> new NoSuchElementException("Something went wrong trying to fetch user object"));
        String userId = String.valueOf(userAuth.getId());

        try {
            // get user and auction
            Auction auction = auctionRepository.findById(auctionId).orElseThrow(
                    () -> new NoSuchElementException("Auction not found with id: " + auctionId)
            );
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new NoSuchElementException("User not found with id: " + userId)
            );

            boolean auctionStatus = auction.isStatus();

            // set user and auction in bids
            bids.setAuction(auction);
            bids.setUser(user);


            if (auctionStatus) {
                // save bids to list in auction only, if we also save in bids we get double save!
                auction.getBids().add(bids);
                auctionRepository.save(auction);
                return ResponseEntity.status(HttpStatus.CREATED).body("Bid successfully placed!");
            } else {
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Bid not placed!");
            }
        } catch (NoSuchElementException e) {
            String errorMessage = "";
            if (e.getMessage().contains("Auction")) {
                errorMessage = "Auction not found with id: " + auctionId;
            } else if (e.getMessage().contains("User")) {
                errorMessage = "User not found with id: " + userId;
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(errorMessage);
        }
    }

    public ResponseEntity<List<Bids>> getBidsByAuction(String id) {
        try {
            Auction auction = auctionRepository.findById(id).orElseThrow(
                    () -> new NoSuchElementException("Auction not found with id: " + id)
            );
            List<Bids> bidsInAuction = auction.getBids();

            return ResponseEntity.ok().body(bidsInAuction);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
