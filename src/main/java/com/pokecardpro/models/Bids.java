package com.pokecardpro.models;


import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "bid")
public class Bids {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(targetEntity = Auction.class)
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    private Auction auction;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Timestamp created;

    private int amount;

    public Bids() {
    }

    public Bids(int id, Auction auction, User user, Timestamp created, int amount) {
        this.id = id;
        this.auction = auction;
        this.user = user;
        this.created = created;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}