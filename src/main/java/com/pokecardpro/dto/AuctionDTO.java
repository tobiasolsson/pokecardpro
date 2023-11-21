package com.pokecardpro.dto;

import com.pokecardpro.models.Shipping;

import java.sql.Timestamp;

// TODO: Behöver vi UserDTO här?
public record AuctionDTO(UserDTO userDTO,
                         String title,
                         String description,
                         boolean status,
                         int buyNow,
                         int reservedPrice,
                         Timestamp startDate,
                         Timestamp endDate,
                         Boolean pickUp,
                         Shipping shipping,
                         int shippingCost,
                         int endBid) {}
