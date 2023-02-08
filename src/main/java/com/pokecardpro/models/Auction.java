package com.pokecardpro.models;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "auction")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(targetEntity = Card.class)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String title;

    private String description;

    private boolean status;

    private int buyNow;

    private int reservedPrice;

    private Timestamp endDate;

    private Boolean pickUp;

    private Shipping shipping;

    private int endBid;

    public Auction() {
    }

    public Auction(int id, Card card, User user, String title, String description, boolean status,
                   int buyNow, int reservedPrice, Timestamp endDate, Boolean pickUp, Shipping shipping, int endBid) {
        this.id = id;
        this.card = card;
        this.user = user;
        this.title = title;
        this.description = description;
        this.status = status;
        this.buyNow = buyNow;
        this.reservedPrice = reservedPrice;
        this.endDate = endDate;
        this.pickUp = pickUp;
        this.shipping = shipping;
        this.endBid = endBid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getBuyNow() {
        return buyNow;
    }

    public void setBuyNow(int buyNow) {
        this.buyNow = buyNow;
    }

    public int getReservedPrice() {
        return reservedPrice;
    }

    public void setReservedPrice(int reservedPrice) {
        this.reservedPrice = reservedPrice;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Boolean getPickUp() {
        return pickUp;
    }

    public void setPickUp(Boolean pickUp) {
        this.pickUp = pickUp;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public int getEndBid() {
        return endBid;
    }

    public void setEndBid(int endBid) {
        this.endBid = endBid;
    }
}
