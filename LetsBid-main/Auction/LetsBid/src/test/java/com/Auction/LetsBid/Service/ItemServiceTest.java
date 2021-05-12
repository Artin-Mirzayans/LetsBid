package com.Auction.LetsBid.Service;

import com.Auction.LetsBid.Controller.ItemController;
import com.Auction.LetsBid.Entity.Item;
import com.Auction.LetsBid.Repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemServiceTest {
    ItemRepository repository;
    ItemService service;
    List<Item> itemList;
    int itemID;
    Item sampleItem;
    Item sampleItem2;

    //@BeforeEach makes that before every @Test method setUp() is run
    @BeforeEach
    public void setUp(){
        //mock of TtemService class
        repository = Mockito.mock(ItemRepository.class);
        service = new ItemService(repository);
        itemID = 1;
        sampleItem = new Item(itemID, "John", "Car", "john123@pizzaland.com", 1000, 0.1, 0, 1000, 2000);//creating an empty item
        itemList = Arrays.asList(sampleItem, sampleItem2);//itemList = an array that has sampleItem in it
    }

    @Test
    public void getItemById_returnsItemIdFromRepo() {
        Mockito.when(repository.findById(itemID)).thenReturn(Optional.of(sampleItem));
        assertEquals(sampleItem, service.getItemById(itemID));
    }
}

