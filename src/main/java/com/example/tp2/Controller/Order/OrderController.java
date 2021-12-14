package com.example.tp2.Controller.Order;


import com.example.tp2.Repository.Order.OrderRepository;
import com.example.tp2.Service.ItemService;
import com.example.tp2.Service.MemberService;
import com.example.tp2.Service.OrderService;
import com.example.tp2.domain.Member;
import com.example.tp2.domain.Order;
import com.example.tp2.domain.OrderItem;
import com.example.tp2.domain.item.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final ItemService itemService;
    private final MemberService memberService;

    @GetMapping("/order/cart")
    public String cart(Model model,HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        try {
            Cookie c = cookies[0];
            Long userId = Long.valueOf(c.getValue());

            Member user = memberService.findOne(userId);



            List<OrderItem> orderItems = orderService.getOrderItems(userId);
            model.addAttribute("orderItems",orderItems);

            Order order = new Order();
            model.addAttribute("order",order);
        }catch (NullPointerException e){
            Member member = new Member();
            model.addAttribute("member",member);
            return "sign/login";
        }







        return "/order/cart";
    }

    @PostMapping("/order/addCart/{itemId}")
    public String addCart(@PathVariable("itemId") Long itemId,HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        Cookie c = cookies[0];
        Long userId = Long.valueOf(c.getValue());
        Member member = memberService.findOne(userId);


        OrderItem orderItem = new OrderItem();
        orderItem.setItem(itemService.findOne(itemId));
        orderItem.setMember(member);

        orderService.saveOrderItem(orderItem);

        return "redirect:/main";
    }
    @PostMapping("/item/form/order/addCart/{itemId}")
    public String addCart2(@PathVariable("itemId") Long itemId,HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        Cookie c = cookies[0];
        Long userId = Long.valueOf(c.getValue());
        Member member = memberService.findOne(userId);
        int count = Integer.valueOf(request.getParameter("count"));
        Item item = itemService.findOne(itemId);

        try {
            item.removeStockQuantity(count);
        }catch (IllegalStateException e){
            //메시지 출력하기
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setMember(member);
        orderItem.setCount(count);

        orderService.saveOrderItem(orderItem);

        return "redirect:/main";
    }

    @PostMapping("/order")
    public String order(@ModelAttribute Order order, HttpServletRequest request) {
        List<Long> ids = order.getIds();
        List<OrderItem> orderItems = order.getOrderItems();
        for (int i = 0; i < ids.size(); i++)
        {
            OrderItem orderItem = orderRepository.findOrderItem(ids.get(i));
            orderItems.add(orderItem);
        }

        Cookie[] cookies = request.getCookies();
        Cookie c = cookies[0];
        Long userId = Long.valueOf(c.getValue());
        Member member = memberService.findOne(userId);

        order.setMember(member);
        orderRepository.regist(order);
        log.info(order.getMember().getName());
        return "redirect:/main";
    }
}
