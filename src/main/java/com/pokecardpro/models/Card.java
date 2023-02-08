package com.pokecardpro.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(targetEntity = Pokemon.class)
    @JoinColumn(name = "poke_id", referencedColumnName = "id")
    private Pokemon pokemon;

    private String qualityRanking;

    private String qualityComment;

    private String image;

    public Card(int id, Pokemon pokemon, String qualityRanking, String qualityComment, String image) {
        this.id = id;
        this.pokemon = pokemon;
        this.qualityRanking = qualityRanking;
        this.qualityComment = qualityComment;
        this.image = image;
    }

    public Card() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

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
}
