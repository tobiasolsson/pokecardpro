package com.pokecardpro.dto;

import java.sql.Timestamp;

public record BidsDTO(UserDTO userDTO, Timestamp created, int amount) {}
