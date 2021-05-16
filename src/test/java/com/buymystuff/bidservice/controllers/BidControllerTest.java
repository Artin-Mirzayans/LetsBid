package com.buymystuff.bidservice.controllers;

import com.buymystuff.bidservice.models.Bid;
import com.buymystuff.bidservice.services.BidService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BidControllerTest {
    BidController controller;
    @Mock
    BidService service;
    Long itemId;
    Bid bid;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new BidController(service);
        itemId = 1L;
        bid = new Bid(1L, 2L, 3L, new Date(), 1.25);
    }

    @Test
    public void getHighestBid_shouldCallServiceGetHighestBidWithItemId() {
        //act
        controller.getHighestBid(itemId);

        //assert
        Mockito.verify(service).getHighestBid(1L);
    }


    @Test
    public void getHighestBid_returnHighestBidFromService(){
        //arrange
        Bid bidExpected = new Bid();
        Mockito.when(service.getHighestBid(itemId)).thenReturn( bidExpected);

        //act:calling
        Bid ret = controller.getHighestBid(itemId);

        //assert
        assertEquals(bidExpected,ret);
    }

    @Test
    public void createBid_shouldCallServiceCreateBidWithBid() {
        //arrange

        //act
        controller.createBid(bid);
        //assert
        Mockito.verify(service).createBid(bid);
    }

    @Test
    public void getCreatedBid_returnCreatedBidFromService(){
        //arrange
        Bid createBid = new Bid();
        Mockito.when(service.createBid(bid)).thenReturn(createBid);
        //act
        Bid ret = controller.createBid(bid);
        //assert
        assertEquals(createBid,ret);
    }



}