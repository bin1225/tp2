package com.example.tp2.Repository.Order;

import com.example.tp2.domain.Order;
import com.example.tp2.domain.OrderItem;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class OrderRepository {

    @PersistenceContext
    EntityManager em;

    public Long regist(Order order){
        em.persist(order);
        return order.getId();
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public List<Order> findAll(){
         return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    public void saveOrderItem(OrderItem orderItem){
        em.persist(orderItem);
    }

    public OrderItem findOrderItem(Long orderItemId){
        return em.find(OrderItem.class,orderItemId);
    }

    public List<OrderItem> findOrderItems(Long memberId){

        return em.createQuery("select oi from OrderItem oi where oi.member.id =: memberId")
                .setParameter("memberId",memberId)
                .getResultList();
    }
}
