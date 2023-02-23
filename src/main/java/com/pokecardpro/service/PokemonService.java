package com.pokecardpro.service;

import com.pokecardpro.models.Pokemon;
import com.pokecardpro.models.Wishlist;
import com.pokecardpro.repository.PokemonRepository;
import com.pokecardpro.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    WishlistRepository wishlistRepository;

    public Pokemon createPokemon(Pokemon thePokemon) {
        return pokemonRepository.save(thePokemon);
    }

    public Pokemon getSinglePokemon(String id) {
        return pokemonRepository.findById(id).get();
    }

    public List<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll();
    }

    public Pokemon updatePokemon(Pokemon thePokemon) {
        return pokemonRepository.save(thePokemon);
    }

    public String deletePokemon(String id) {
        pokemonRepository.deleteById(id);
        return "id " + id + " has been deleted";
    }

    public Pokemon savePokemonToWishlist(Pokemon pokemon, String wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow();
        wishlist.getPokemons().add(pokemon);
        pokemonRepository.saveAll(wishlist.getPokemons());
        return pokemon;
    }
}
