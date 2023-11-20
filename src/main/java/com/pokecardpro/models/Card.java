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

    public void setQualityRanking(String qualityRanking) {
        this.qualityRanking = qualityRanking;
    }

    public String getQualityComment() {
        return qualityComment;
    }

    public void setQualityComment(String qualityComment) {
        this.qualityComment = qualityComment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
