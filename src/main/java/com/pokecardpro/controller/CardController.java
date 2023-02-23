package com.pokecardpro.controller;

import com.pokecardpro.models.Card;
import com.pokecardpro.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping("cards")
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("card/{id}")
    public Card getCardById(@PathVariable String id) {
        return cardService.getCardById(id);
    }

    @PostMapping("card")
    public Card createCard(@RequestBody Card card) {
        return cardService.createCard(card);
    }

    @PutMapping("card")
    public Card updateCard(@RequestBody Card card) {
        return cardService.updateCard(card);
    }

    @DeleteMapping("card/delete/{id}")
    public String deleteCard(@PathVariable String id) {
        return cardService.deleteCard(id);
    }
}
