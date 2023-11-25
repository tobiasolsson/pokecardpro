package com.pokecardpro.auth;

import com.pokecardpro.models.Watchlist;
import com.pokecardpro.models.Wishlist;

public record RegisterRequest(String firstName, String lastName, String email, String password, int phone,
                              String street, int streetNr, String city, int zipCode, Watchlist watchlist, Wishlist wishlist) {}
