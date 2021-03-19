package com.Auction.LetsBid.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String photo;
    private String ownerId;
    private double currentBid;
    private double bidInitial;
    private double bidIncrement;
    private double bidStartDateTime;
    private double bidEndDateTime;
}
