package com.pokecardpro.models;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToOne(mappedBy = "wishlist")
    private Pokemon pokemon;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;


    public Wishlist() {
    }

    public Wishlist(String name, Pokemon pokemon, User user) {
        this.name = name;
        this.pokemon = pokemon;
        this.user = user;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
