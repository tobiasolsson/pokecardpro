package com.pokecardpro.service;

import com.pokecardpro.models.Pokemon;
import com.pokecardpro.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PokemonService {
    @Autowired
    PokemonRepository pokemonRepository;

    @Transactional
    public String deletePokemonFromWishlist(int pokemonId, int wishlistId) {
        int sizeOfRowsDeleted = pokemonRepository.deletePokemonByPokemonIdAndWishListId(pokemonId, wishlistId);
        if (sizeOfRowsDeleted == 0) {
            return "Could not delete pokemon from wishlist";
        }
        return "Pokemon has been deleted from wishlist successfully";
    }
}