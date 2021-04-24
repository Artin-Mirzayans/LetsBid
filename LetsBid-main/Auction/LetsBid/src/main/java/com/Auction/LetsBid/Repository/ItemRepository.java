package com.Auction.LetsBid.Repository;

import com.Auction.LetsBid.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    Item findByName(String name);
}
