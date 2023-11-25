package com.pokecardpro.auth;

import com.pokecardpro.models.Watchlist;


public record RegisterRequest(String firstName, String lastName, String email, String password, int phone,
                              String street, int streetNr, String city, int zipCode, Watchlist watchlist ) {}
