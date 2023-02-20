package com.pokecardpro.controller;

import com.pokecardpro.models.Pokemon;
import com.pokecardpro.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    @DeleteMapping("pokemon/{pokemonId}/{wishlistId}")
    public String deletePokemonFromWishlist(@PathVariable String pokemonId,
                                             @PathVariable String wishlistId ) {
        return pokemonService.deletePokemonFromWishlist(pokemonId, wishlistId);
    }

    @GetMapping ("pokemonFromWishlist/{userId}")
    public Set<Pokemon> getAllPokemonFromUserWishlist(@PathVariable String userId) {
        return pokemonService.getAllPokemonFromUserWishlist(userId);
    }
}