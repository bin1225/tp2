package com.example.tp2.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tp2.repository.order.OrderRepository;
import com.example.tp2.service.ItemService;
import com.example.tp2.service.MemberService;
import com.example.tp2.service.OrderService;
import com.example.tp2.domain.Member;
import com.example.tp2.domain.Order;
import com.example.tp2.domain.OrderItem;
import com.example.tp2.domain.item.Item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final OrderRepository orderRepository;
	private final ItemService itemService;
	private final MemberService memberService;

	@GetMapping("/order/cart")
	public String cart(Model model, HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();
		try {
			Cookie c = cookies[0];
			Long userId = Long.valueOf(c.getValue());

			List<OrderItem> orderItems = orderService.getOrderItems(userId);
			List<OrderItem> ois = new ArrayList<>();

			for (int i = 0; i < orderItems.size(); i++) {
				if (!orderItems.get(i).isOrdered()) {
					ois.add(orderItems.get(i));
				}
			}

			model.addAttribute("orderItems", ois);

			Order order = new Order();
			model.addAttribute("order", order);
		} catch (NullPointerException e) {
			Member member = new Member();
			model.addAttribute("member", member);
			model.addAttribute("objectError", new ObjectError("member", "로그인이 필요한 서비스입니다."));

			return "sign/login";
		}

		return "/order/cart";
	}

	@PostMapping("/order/addCart/{itemId}")
	public String addCart(@PathVariable("itemId") Long itemId, HttpServletRequest request, Model model) {
		try {
			Cookie[] cookies = request.getCookies();
			Cookie c = cookies[0];
			Long userId = Long.valueOf(c.getValue());
			Member member = memberService.findOne(userId);

			OrderItem orderItem = new OrderItem();
			Item item = itemService.findOne(itemId);

			try {
				item.removeStockQuantity(1);
			} catch (IllegalStateException e) {
				//메시지 출력하기
			}

			orderItem.setItem(item);
			orderItem.setMember(member);
			orderItem.setOrderPrice(item.getPrice(), 1);
			orderItem.setCount(1);
			orderService.saveOrderItem(orderItem);

			return "redirect:/main";
		} catch (NullPointerException e) {
			Member member = new Member();
			model.addAttribute("member", member);
			model.addAttribute(new ObjectError("member", "로그인이 필요한 서비스입니다."));
			return "sign/login";
		}
	}

	@PostMapping("/item/form/order/addCart/{itemId}")
	public String addCart2(@PathVariable("itemId") Long itemId, HttpServletRequest request, Model model) {

		try {
			Cookie[] cookies = request.getCookies();
			Cookie c = cookies[0];
			Long userId = Long.valueOf(c.getValue());
			Member member = memberService.findOne(userId);
			int count = Integer.valueOf(request.getParameter("count"));
			Item item = itemService.findOne(itemId);

			try {
				item.removeStockQuantity(count);
			} catch (IllegalStateException e) {
				//메시지 출력하기
			}
			OrderItem orderItem = new OrderItem();
			orderItem.setItem(item);
			orderItem.setMember(member);
			orderItem.setCount(count);
			orderItem.setOrderPrice(item.getPrice(), count);

			orderService.saveOrderItem(orderItem);

			return "redirect:/main";
		} catch (NullPointerException e) {
			Member member = new Member();
			model.addAttribute("member", member);
			model.addAttribute(new ObjectError("member", "로그인이 필요한 서비스입니다."));
			return "sign/login";
		}
	}

	@PostMapping("/order")
	public String order(@ModelAttribute Order order, HttpServletRequest request) {
		List<Long> ids = order.getIds();
		List<OrderItem> orderItems = order.getOrderItems();
		for (int i = 0; i < ids.size(); i++) {
			OrderItem orderItem = orderRepository.findOrderItem(ids.get(i));

			orderItem.setOrder(order);
			orderItem.setOrdered(true);
			orderItems.add(orderItem);
		}

		Cookie[] cookies = request.getCookies();
		Cookie c = cookies[0];
		Long userId = Long.valueOf(c.getValue());
		Member member = memberService.findOne(userId);

		order.setMember(member);
		orderRepository.regist(order);

		return "redirect:/main";
	}
}
