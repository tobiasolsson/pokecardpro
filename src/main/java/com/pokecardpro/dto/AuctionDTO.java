package com.pokecardpro.dto;

import com.pokecardpro.models.Shipping;

import java.sql.Timestamp;

public record AuctionDTO(int id,
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
