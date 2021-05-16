package com.buymystuff.bidservice.services;

import com.buymystuff.bidservice.models.Bid;
import com.buymystuff.bidservice.repositories.BidRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BidService {
    BidRepository repository;

    BidService(BidRepository repository) {
        this.repository = repository;
    }

    public Bid getHighestBid(Long itemId) {
        Bid res = repository.findFirstByItemIdOrderByAmountDesc(itemId);
        if (res == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There are no bids with that itemId");
        } else {
            return res;
        }
    }

    public Bid createBid(Bid bid) {
        if (bid.getItemId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "itemId cannot be null");
        } else if (bid.getBidderId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bidderId cannot be null");
        } else if (bid.getAmount() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "amount cannot be null");
        } else if (bid.getDatetime() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "datetime cannot be null");
        } else if(bid.getId()!= null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be null");
        } return repository.save(bid);
    }
}