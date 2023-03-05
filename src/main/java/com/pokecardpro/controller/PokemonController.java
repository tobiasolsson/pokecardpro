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

    @PostMapping("createpokemon")
    public Pokemon createPokemon(@RequestBody Pokemon thePokemon) {
        return pokemonService.createPokemon(thePokemon);
    }

    @GetMapping("getpokemon/{id}")
    public Pokemon getSinglePokemon(@PathVariable String id) {
        return pokemonService.getSinglePokemon(id);
    }

    @GetMapping("allpokemon")
    public List<Pokemon> getAllPokemon() {
        return pokemonService.getAllPokemon();
    }

    @PutMapping("updatepokemon")
    public Pokemon updatePokemon(@RequestBody Pokemon thePokemon) {
        return pokemonService.updatePokemon(thePokemon);
    }
    @DeleteMapping("pokemon/{pokemonId}/{wishlistId}")
    public String deletePokemonFromWishlist(@PathVariable int pokemonId, @PathVariable int wishlistId ) {
        return pokemonService.deletePokemonFromWishlist(pokemonId, wishlistId);
    }

    @DeleteMapping("deletepokemon/{id}")
    public String deletePokemon(@PathVariable String id) {
        return pokemonService.deletePokemon(id);
    }

    @PostMapping("savePokemonToWishlist/{wishlistId}")
    public Pokemon savePokemonToWishlist(@RequestBody Pokemon pokemon, @PathVariable String wishlistId) {
        return pokemonService.savePokemonToWishlist(pokemon, wishlistId);
    }

    /*@GetMapping("pokemonFromWishlist/{userId}")
    public Set<Pokemon> getAllPokemonFromUserWishlist(@PathVariable String userId) {
        return pokemonService.getAllPokemonFromUserWishlist(userId);
    }*/
}