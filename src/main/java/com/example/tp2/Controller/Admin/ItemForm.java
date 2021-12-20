package com.example.tp2.Controller.Admin;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemForm {


    private Long id;
    private String name;
    private String category;
    private String imgUrl;
    private int price;
    private int stockQuantity;
    private String isbn;
    private String author;


}
