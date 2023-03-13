package com.pokecardpro.controller;
import com.pokecardpro.models.Bids;
import com.pokecardpro.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class BidController {

    @Autowired
    BidService bidService;

    @PostMapping("bids")
    public ResponseEntity<String> placeBid (@RequestBody Bids bids) {
        return bidService.placeBid(bids);
    }
}
