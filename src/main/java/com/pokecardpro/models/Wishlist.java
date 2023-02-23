package com.pokecardpro.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany(mappedBy = "wishlist")
    private Set<Pokemon> pokemons;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;


    public Wishlist() {
    }

    public Wishlist(String name, Set<Pokemon> pokemons, User user) {
        this.name = name;
        this.pokemons = pokemons;
        this.user = user;
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

    public Set<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(Set<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
