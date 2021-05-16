package com.buymystuff.bidservice.controllers;

import com.buymystuff.bidservice.models.Bid;
import com.buymystuff.bidservice.services.BidService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BidController.class)
class BidControllerIT {
    MockMvc mockMvc;

    @SpyBean
    BidController controller;

    @MockBean
    BidService service;

    @Captor
    private ArgumentCaptor<Bid> bidCaptor;
    
    private Bid bid;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);
        //Gives you all the context. Allow you to use spring specific annotations
        //Lets us use @MockBean
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        bid = new Bid(1L, 2L, 3L, new Date(), 1.25);
    }

    @Test
    public void getHighestBid_shouldReturn200Status() throws Exception {
        //act                                               /assert
        mockMvc.perform(get("/bids/2/highest")).andExpect(status().isOk());
    }

    @Test
    public void getHighestBid_shouldCallGetHighestBidServiceMethodWithPathVar() throws Exception {
        //act                                               /assert
        mockMvc.perform(get("/bids/2/highest")).andExpect(status().isOk());
        //assert
        Mockito.verify(service).getHighestBid(2L);
    }

    @Test
    public void createBid_shouldReturn201Status() throws Exception {
        String requestBody = new ObjectMapper().writeValueAsString(bid);
        mockMvc.perform(post("/bids").contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isCreated());

    }

    @Test
    public void createBid_shouldCallCreateBidServiceWithRequestBody() throws Exception {
        // *Translates the bid object to JSON * Body is written in JSON, telling this object into json
        String requestBody = new ObjectMapper().writeValueAsString(bid);
        mockMvc.perform(post("/bids").contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isCreated());
        Mockito.verify(service).createBid(bidCaptor.capture());
        assertEquals(bid.getAmount(), bidCaptor.getValue().getAmount());
    }
}