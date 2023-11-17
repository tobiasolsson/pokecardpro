package com.pokecardpro.dto;

public record UserDTO(String firstName,
                      String lastName,
                      String email,
                      int phone,
                      String street,
                      int streetNr,
                      String city,
                      int zipCode) {}
