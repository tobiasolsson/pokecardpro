package com.pokecardpro.repository;

import com.pokecardpro.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, String> {

    @Query("delete p from Pokemon p join Wishlist w on p.id = w.id where p.id = :pokemonId and w.id = :wishlistId")
    String deletePokemonByPokemonIdAndWishListId(@Param("pokemonId") String pokemonId, @Param("wishlistId") String wishlistId);
}
