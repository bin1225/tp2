package com.example.tp2.Service;

import com.example.tp2.Repository.Order.OrderRepository;
import com.example.tp2.domain.Order;
import com.example.tp2.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepositroy;

    public Long createOrder(Order order)
    {
        orderRepositroy.regist(order);
        return order.getId();
    }



    public Long saveOrderItem(OrderItem orderItem){

        orderRepositroy.saveOrderItem(orderItem);
        return orderItem.getId();
    }
    public List<OrderItem> getOrderItems(Long userId){
        List<OrderItem> orderItems = orderRepositroy.findOrderItems(userId);
        return orderItems;
    }

}
