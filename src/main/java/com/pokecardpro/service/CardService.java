package com.pokecardpro.service;

import com.pokecardpro.models.Card;
import com.pokecardpro.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(String id) {
        return cardRepository.findById(id).get();
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    /*
    public Card createCard(Card card) {
        // get actual user from userId in auction and set it in auction
        String pokemonId = Integer.toString(card.getPokemon().getId());
        Pokemon pokemon = pokemonRepository.findById(pokemonId).get();
        card.setPokemon(pokemon);

        return cardRepository.save(card);
    }
    */

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    public String deleteCard(String id) {
        cardRepository.deleteById(id);
        return "card " + id + " has been deleted";
    }
}
