package com.pokecardpro.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String image;
    private String set;
    private String rarity;
    private int cardNumber;
    private String description;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "wishlist_id", referencedColumnName = "id")
    private Wishlist wishlist;


    public Pokemon() {
    }

    public Pokemon(String name, String image, String set, String rarity, int cardNumber, String description,
                   Wishlist wishlist) {
        this.name = name;
        this.image = image;
        this.set = set;
        this.rarity = rarity;
        this.cardNumber = cardNumber;
        this.description = description;
        this.wishlist = wishlist;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getImage() {
        return image;
    }


    public String getSet() {
        return set;
    }


    public String getRarity() {
        return rarity;
    }


    public int getCardNumber() {
        return cardNumber;
    }


    public String getDescription() {
        return description;
    }


    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }
}
