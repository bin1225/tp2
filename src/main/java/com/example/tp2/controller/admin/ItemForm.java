package com.example.tp2.controller.admin;

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


}
