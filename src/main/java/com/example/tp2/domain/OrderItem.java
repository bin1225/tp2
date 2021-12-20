package com.example.tp2.domain;

import com.example.tp2.domain.item.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name ="orderItem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name ="order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="item_id")
    private Item item;


    private boolean ordered;

    private int count;

    private int orderPrice;

    public void setOrderPrice(int itemPrice, int itemCount){
        this.orderPrice = itemPrice * itemCount;
    }


}
