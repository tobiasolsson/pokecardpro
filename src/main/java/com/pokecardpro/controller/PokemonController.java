package com.pokecardpro.controller;

import com.pokecardpro.models.Pokemon;
import com.pokecardpro.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("deletepokemon/{id}")
    public String deletePokemon(@PathVariable String id) {
        return pokemonService.deletePokemon(id);
    }
    @PostMapping("savePokemonToWishlist/{wishlistId}")
    public Pokemon savePokemonToWishlist(@RequestBody Pokemon pokemon, @PathVariable String wishlistId) {
        return pokemonService.savePokemonToWishlist(pokemon, wishlistId);
    }
}
