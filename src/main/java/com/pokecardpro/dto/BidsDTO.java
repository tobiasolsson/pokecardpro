package com.pokecardpro.dto;

import java.sql.Timestamp;

public record BidsDTO(String firstName, Timestamp created, int amount) {}
