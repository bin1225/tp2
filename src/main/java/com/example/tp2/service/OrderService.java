package com.example.tp2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tp2.repository.order.OrderRepository;
import com.example.tp2.domain.Order;
import com.example.tp2.domain.OrderItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepositroy;

	public Long createOrder(Order order) {
		orderRepositroy.regist(order);
		return order.getId();
	}

	public Long saveOrderItem(OrderItem orderItem) {

		orderRepositroy.saveOrderItem(orderItem);
		return orderItem.getId();
	}

	public List<OrderItem> getOrderItems(Long userId) {
		List<OrderItem> orderItems = orderRepositroy.findOrderItems(userId);
		return orderItems;
	}

}
