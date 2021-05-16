package com.buymystuff.bidservice.controllers;

import com.buymystuff.bidservice.models.Bid;
import com.buymystuff.bidservice.services.BidService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bids")
@CrossOrigin("*")
public class BidController {

    //connects to the service
    private BidService bidService;

    //used in testing to pass in mock service
    BidController(BidService service) {
        this.bidService = service;
    }

    //will be at GET /bids/{itemId}/highest
    //returns Bid
    //calls to bidService.getHighestBid(long itemId)
    //used to be able to show the highest (current) bid on an item

    //Used in integration test
    //Used because its a get HTTTP requestion
    
    @GetMapping("/{itemId}/highest")
    //Item id is a path variable
    public Bid getHighestBid(@PathVariable Long itemId) {
        return bidService.getHighestBid(itemId);
    }

    //will be at POST /bids
    //returns Bid (default response 201 Created)
    //response body will contain bid object which will be passed to service
    //calls to bidService.createBid(Bid bid)
    //create new bid
    //used when a user needs to create a new bid
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bid createBid(@RequestBody Bid bid) {
        return bidService.createBid(bid);
    }

}
