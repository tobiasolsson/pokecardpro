package com.pokecardpro.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "wishlist_name")
    private String name;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL)
    private Set<Pokemon> pokemons;

    public Wishlist() {
    }

    public Wishlist(String name, Set<Pokemon> pokemons) {
        this.name = name;
        this.pokemons = pokemons;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(Set<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
