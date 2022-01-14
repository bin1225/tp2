package com.example.tp2.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;

	private String name;

	private String userId;
	private String password;

	@OneToMany(mappedBy = "member")
	private List<OrderItem> orderItemList = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<Order> orderList = new ArrayList<>();

}
