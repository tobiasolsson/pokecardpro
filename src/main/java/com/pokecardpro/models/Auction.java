package com.pokecardpro.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "auction")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = {CascadeType.ALL}, targetEntity = Card.class)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card cardId;

    @OneToOne(cascade = {CascadeType.ALL}, targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    private boolean status;

    private int buyNow;

    private int reservedPrice;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp startDate;

    //yyyy-MM-dd-HH-mm-ss.zzz
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp endDate;

    private Boolean pickUp;

    //@Enumerated(EnumType.STRING)
    private Shipping shipping;

    private int shippingCost;

    private int endBid;

    public Auction() {
    }

    public Auction(Card cardId, User userId, String title, String description, boolean status, int buyNow,
                   int reservedPrice, Timestamp startDate, Timestamp endDate, Boolean pickUp, Shipping shipping,
                   int shippingCost, int endBid) {
        this.cardId = cardId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.buyNow = buyNow;
        this.reservedPrice = reservedPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickUp = pickUp;
        this.shipping = shipping;
        this.shippingCost = shippingCost;
        this.endBid = endBid;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public int getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(int shippingCost) {
        this.shippingCost = shippingCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Card getCardId() {
        return cardId;
    }

    public void setCardId(Card cardId) {
        this.cardId = cardId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
