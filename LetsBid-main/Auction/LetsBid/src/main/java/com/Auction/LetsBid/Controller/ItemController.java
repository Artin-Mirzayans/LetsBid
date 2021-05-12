package com.Auction.LetsBid.Controller;

import com.Auction.LetsBid.Entity.Item;
import com.Auction.LetsBid.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//     @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Item saveItem(@RequestBody Item item) {
//        return itemService.saveItem(item);
//    }

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item addItem(@RequestBody Item item) {
        return service.saveItem(item);
    }
//    @PostMapping("/addItems")
//    public List<Item> addItems(@RequestBody List<Item> items) {
//        return service.saveItems(items);
//    }

    @GetMapping
    public List <Item> findallItems() {
        return service.getItems();
    }
//    @GetMapping(value = "{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Item findItemById(@PathVariable int id) {
        return service.getItemById(id);
    }


    @PutMapping("/update")
    public Item updateItem(@RequestBody Item item) {
        return service.updateItem(item);
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable int id) {
        service.deleteItem(id);
    }
}

//  @DeleteMapping("/{itemId}")
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void deleteItem(@PathVariable Long itemId) {
//        itemService.deleteItem(itemId);
//    }
