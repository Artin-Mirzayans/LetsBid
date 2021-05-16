package com.buymystuff.bidservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Bid {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private Long bidderId;

    @Column(nullable = false)
    private Date datetime;

    @Column(nullable = false)
    private Double amount;

    public Bid() { }

    public Bid(Long id, Long itemId, Long bidderId, Date datetime, Double amount) {
        this.id = id;
        this.itemId = itemId;
        this.bidderId = bidderId;
        this.datetime = datetime;
        this.amount = amount;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }
    public Long getBidderId() { return bidderId; }
    public void setBidderId(Long bidderId) { this.bidderId = bidderId; }
    public Date getDatetime() { return datetime; }
    public void setDatetime(Date datetime) { this.datetime = datetime; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
