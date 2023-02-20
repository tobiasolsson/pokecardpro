package com.pokecardpro.service;

import com.pokecardpro.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {
    @Autowired
    PokemonRepository pokemonRepository;

    public String deletePokemonFromWishlist(String pokemonId, String wishlistId) {
        pokemonRepository.deletePokemonByPokemonIdAndWishListId(pokemonId, wishlistId);
        return "Pokemon " + pokemonId + " has been deleted from Wishlist!";
    }
}