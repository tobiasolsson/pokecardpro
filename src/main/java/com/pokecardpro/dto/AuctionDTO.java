package com.pokecardpro.dto;

import com.pokecardpro.models.Shipping;

import java.sql.Timestamp;
import java.util.List;

public record AuctionDTO(UserDTO userDTO,
                         List<BidsDTO> bidsDTOList,
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
