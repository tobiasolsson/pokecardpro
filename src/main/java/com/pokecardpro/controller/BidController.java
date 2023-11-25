package com.pokecardpro.controller;
import com.pokecardpro.dto.BidsDTO;
import com.pokecardpro.models.Bids;
import com.pokecardpro.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/")
public class BidController {

    @Autowired
    BidService bidService;

    @PostMapping("bids")
    public ResponseEntity<String> placeBid (@RequestBody Bids bids) {
        return bidService.placeBid(bids);
    }

    @GetMapping("bids/{id}")
    public ResponseEntity<List<BidsDTO>> getAllBidsInAuction(@PathVariable String id) {
        return bidService.getBidsByAuction(id);
    }
}
