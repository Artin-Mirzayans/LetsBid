package com.Auction.LetsBid.Controller;


import com.Auction.LetsBid.Entity.Item;
import com.Auction.LetsBid.Service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;


public class ItemControllerTest {
    ItemController itemController;
    ItemService service;
    List<Item> itemList;
    int itemID;
    int itemID2;
    Item sampleItem;
    Item sampleItem2;

    //@BeforeEach makes that before every @Test method setUp() is run
    @BeforeEach
    public void setUp(){
        //mock of TtemService class
        service = Mockito.mock(ItemService.class);
        itemController = new ItemController(service);
        itemID = 1;
        sampleItem = new Item(itemID, null, null, null, 0, 0, 0, 0, 0);//creating an empty item
        itemList = Arrays.asList(sampleItem, sampleItem2);//itemList = an array that has sampleItem in it
    }

    //three steps of testing:
    //given
    //when
    //then (assert)
    @Test
    public void getAllItem_returnsEmptyArray(){
        //parameters for assertEquals: (expected, given)
        assertEquals(Collections.emptyList(), itemController.findallItems());
    }

    @Test
    public void getAllItem_returnAllItemsFromService(){
        //Mockito.when() checks if parameter is called
        Mockito.when(service.getItems()).thenReturn(itemList);
        assertEquals(itemList, itemController.findallItems());
    }

    @Test
    public void findItembyID_callsServiceGetItembyID(){
        itemController.findItemById(itemID);
        //Mockito.verify makes sure that service is being called, if service is not being called then test would fail
        Mockito.verify(service).getItemById(itemID);
    }

    @Test
    public void updateItem_returnsUpdatedItem() {
        Mockito.when(service.updateItem(sampleItem)).thenReturn(sampleItem);
        assertEquals(sampleItem, itemController.updateItem(sampleItem));
    }

    @Test
    public void deleteItem_callsDeletedItemService() {
        itemController.deleteItem(itemID);
        Mockito.verify(service).deleteItem(itemID);
    }


}