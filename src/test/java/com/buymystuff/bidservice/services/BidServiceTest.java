package com.buymystuff.bidservice.services;

import com.buymystuff.bidservice.models.Bid;
import com.buymystuff.bidservice.repositories.BidRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BidServiceTest {
    BidService service;
    Bid bid;
    @Mock
    BidRepository repository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.service = new BidService(repository);
        bid = new Bid(1L, 2L, 3L, new Date(), 1.25);
    }

    @Test
    public void getHighestBid_throwsResponseStatusExceptionWith204IfNoBidsForItem() {
        //arrange
        Mockito.when(repository.findFirstByItemIdOrderByAmountDesc(1L)).thenReturn(null);

                                            //assert
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            //act
            service.getHighestBid(1L);
        });
        //assert
        assertEquals(HttpStatus.NO_CONTENT, thrown.getStatus());
    }

    @Test
    public void getHighestBid_returnsHighestBidFromRepository() {
        //arrange
        Mockito.when(repository.findFirstByItemIdOrderByAmountDesc(1L)).thenReturn(bid);
        //act
        Bid res = service.getHighestBid(1L);
        //assert
        assertEquals(bid,res );
    }

    @Test
    public void createBid_throws400StatusExceptionWithNullItemId() {
        //arrange
        bid.setItemId(null);
        //assert
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            //act
            service.createBid(bid);
        });
        //assert
        assertEquals(HttpStatus.BAD_REQUEST,thrown.getStatus());
    }

    @Test
    public void createBid_throws400StatusExceptionWithNullBidderId() {
        //arrange
        bid.setBidderId(null);
        //assert
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            //act
            service.createBid(bid);
        });
        //assert
        assertEquals(HttpStatus.BAD_REQUEST,thrown.getStatus());
    }

    @Test
    public void createBid_throws400StatusExceptionWithNullAmount() {
        //arrange
        bid.setAmount(null);
        //assert
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            //act
            service.createBid(bid);
        });
        //assert
        assertEquals(HttpStatus.BAD_REQUEST,thrown.getStatus());
    }

    @Test
    public void createBid_throws400StatusExceptionWithNullDatetime() {
        //arrange
        bid.setDatetime(null);
        //assert
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            //act
            service.createBid(bid);
        });
        //assert
        assertEquals(HttpStatus.BAD_REQUEST,thrown.getStatus());
    }

    @Test
    public void createBid_throws400StatusExceptionWithNotNullId() {
        //arrange
        bid.setId(1L);
        //assert
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            //act
            service.createBid(bid);
        });
        //assert
        assertEquals(HttpStatus.BAD_REQUEST,thrown.getStatus());
    }

    @Test
    public void createBid_returnsCreatedBidFromRepository() {
        //arrange
        bid.setId(null);
        Mockito.when(repository.save(bid)).thenReturn(bid);
        //act
        Bid res = service.createBid(bid);
        //assert
        assertEquals(bid,res );
    }
}