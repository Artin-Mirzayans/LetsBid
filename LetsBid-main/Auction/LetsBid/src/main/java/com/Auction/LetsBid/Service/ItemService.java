package com.Auction.LetsBid.Service;

import com.Auction.LetsBid.Entity.Item;
import com.Auction.LetsBid.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    // Change else ifs to if ???
    public Item saveItem(Item item) {
        if (item.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be empty");
        }

        else if (item.getPhoto() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Photo cannot be empty");
        }

        else if (item.getOwnerId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Owner ID cannot be empty");
        }

        else if (item.getBidInitial() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current bid cannot be 0");
        }

        // Do Time/Date error handling

        return repository.save(item);
    }

//    public List<Item> saveItems(List<Item> items) {
//        return repository.saveAll(items);
//    }
    public List<Item> getItems() {
        return repository.findAll();
    }
    public Item getItemById(int id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item with that ID does not exist."));
    }

    public void deleteItem (int id) {
        //does the item id exist?
        //if it exists the delete
        //else
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item to delete does not exist");
        }
    }

    public Item updateItem(Item item) {
        if (item.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be empty");
        }
        else if (item.getPhoto() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Photo cannot be empty");
        }

        repository.findById(item.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item with that id doesnt exist"));
        return repository.save(item);

    }
}
//        Item existingItem = repository.findById(item.getId()).orElse(null);
//        existingItem.setName(item.getName());
//        existingItem.setCurrentBid(item.getCurrentBid());