package com.pokecardpro.repository;

import com.pokecardpro.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, String> {

    @Modifying
    @Query(value = "delete from pokemon poke where poke.id = :pokemonId and poke.wishlist_id = :wishlistId", nativeQuery = true)
    int deletePokemonByPokemonIdAndWishListId(@Param("pokemonId") int pokemonId, @Param("wishlistId") int wishlistId);

}
