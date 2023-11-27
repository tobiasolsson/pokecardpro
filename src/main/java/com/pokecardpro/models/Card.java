package com.pokecardpro.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String qualityRanking;

    private String qualityComment;

    private String image;

    private String name;

    private String set;

    private int rarity;

    private int cardNumber;

    private String description;

    public String getQualityRanking() {
        return qualityRanking;
    }

    public String getQualityComment() {
        return qualityComment;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getSet() {
        return set;
    }

    public int getRarity() {
        return rarity;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getDescription() {
        return description;
    }

    public Card() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
