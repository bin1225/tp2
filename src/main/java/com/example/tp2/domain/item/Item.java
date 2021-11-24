package com.example.tp2.domain.item;

import com.example.tp2.domain.Category;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)// 상속 구현 전략 선택
public class Item {

    @Id
    @GeneratedValue
    @Column(name ="item_id")
    private Long id;

    private String name;



    private int stockQuantity;

    private int price;



    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    protected Item(){

    }
    public Item(String name,Integer price) {
        this.name = name;
        this.price = price;
    }



    public void addStockQuantity(int count){
        this.stockQuantity +=count;
    }

    public void removeStockQuantity (int count){
        if(count>stockQuantity)
        {
            throw new IllegalStateException("재고가 부족합니다");
        }
        else {
            this.stockQuantity -=count;
        }

    }
}
