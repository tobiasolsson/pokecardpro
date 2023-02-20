package com.pokecardpro.service;

import com.pokecardpro.models.Pokemon;
import com.pokecardpro.models.User;
import com.pokecardpro.repository.PokemonRepository;
import com.pokecardpro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PokemonService {
    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    UserRepository userRepository;


    public String deletePokemonFromWishlist(String pokemonId, String wishlistId) {
        pokemonRepository.deletePokemonByPokemonIdAndWishListId(pokemonId, wishlistId);
        return "Pokemon " + pokemonId + " has been deleted from Wishlist!";
    }

    public Set<Pokemon> getAllPokemonFromUserWishlist(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getWishlist().getPokemons();
    }
}