package com.pokecardpro.service;

import com.pokecardpro.models.Pokemon;
import com.pokecardpro.models.User;
import com.pokecardpro.models.Wishlist;
import com.pokecardpro.repository.PokemonRepository;
import com.pokecardpro.repository.UserRepository;
import com.pokecardpro.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class PokemonService {
    @Autowired
    PokemonRepository pokemonRepository;
    @Autowired
    WishlistRepository wishlistRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public String deletePokemonFromWishlist(int pokemonId, int wishlistId) {
        int sizeOfRowsDeleted = pokemonRepository.deletePokemonByPokemonIdAndWishListId(pokemonId, wishlistId);
        if (sizeOfRowsDeleted == 0) {
            return "Could not delete pokemon from wishlist";
        }
        return "Pokemon has been deleted from wishlist successfully";
    }

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
        pokemon.setWishlist(wishlist);
        pokemonRepository.save(pokemon);
        return pokemon;
    }

    public Set<Pokemon> getAllPokemonFromUserWishlist(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getWishlist().getPokemons();
    }
}
