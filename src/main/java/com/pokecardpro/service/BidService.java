package com.pokecardpro.service;
import com.pokecardpro.models.Bids;
import com.pokecardpro.repository.BidsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidService {

    @Autowired
    BidsRepository bidsRepository;

    public Bids placeBid (Bids bids) {
        return bidsRepository.save(bids);

    }
}
