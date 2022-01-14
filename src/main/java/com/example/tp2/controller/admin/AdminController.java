package com.example.tp2.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tp2.domain.Member;
import com.example.tp2.domain.Order;
import com.example.tp2.domain.OrderItem;
import com.example.tp2.domain.item.Item;
import com.example.tp2.repository.member.MemberRepository;
import com.example.tp2.repository.order.OrderRepository;
import com.example.tp2.service.ItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("checkstyle:RegexpMultiline")
@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

	private final MemberRepository memberRepository;
	private final ItemService itemService;
	private final OrderRepository orderRepository;

	@ModelAttribute("categories")
	public List<String> category() {
		List<String> categories = new ArrayList<>();
		categories.add("top");
		categories.add("trouser");
		categories.add("shoe");

		return categories;
	}

	@GetMapping("/admin/main")
	public String main() {
		return "admin/home";
	}

	@GetMapping("/admin/members")
	public String getmemberList(Model model) {

		List<Member> members = memberRepository.findAll();
		model.addAttribute("members", members);

		return "admin/memberList";
	}

	@GetMapping("/items/new")
	public String create_NewItem(Model model) {
		ItemForm form = new ItemForm();
		model.addAttribute("form", form);

		return "admin/items/newItem";
	}

	@GetMapping("/items")
	public String getItems(Model model) {

		List<Item> itemList = itemService.findAll();
		model.addAttribute("items", itemList);

		return "admin/items/list";
	}



	@PostMapping("/items/new")
	public String registItem(@ModelAttribute ItemForm itemForm) {

		Item item = new Item(itemForm.getName(), itemForm.getPrice(), itemForm.getCategory());
		if (itemForm.getImgUrl() == null) {
			item.setImgUrl("https://image.thehyundai.com/static/image/sect/hnm/cpnt/hnmmain1344420020211103141541.png");
		} else {
			item.setImgUrl(itemForm.getImgUrl());
		}
		itemService.save(item);

		return "admin/home";
	}

	@GetMapping("items/{itemId}/edit")
	public String geteditForm(@ModelAttribute("form") ItemForm itemForm, Model model
		, @PathVariable("itemId") Long id) {
		Item item = itemService.findOne(id);
		itemForm.setName(item.getName());
		itemForm.setPrice(item.getPrice());
		itemForm.setStockQuantity(item.getStockQuantity());
		model.addAttribute("form", itemForm);
		return "admin/items/editForm";
	}

	@PostMapping("items/{itemId}/edit")
	public String editItem(@PathVariable("itemId") Long id, @ModelAttribute("form") ItemForm itemForm) {
		Item item = itemService.findOne(id);
		item.setName(itemForm.getName());
		item.setPrice(itemForm.getPrice());
		item.setStockQuantity(itemForm.getStockQuantity());
		item.setCategory(itemForm.getCategory());
		itemService.save(item);
		return "admin/home";
	}

	@PostMapping("items/{itemId}/delete")
	public String deleteItem(@PathVariable("itemId") Long id, @ModelAttribute("form") ItemForm itemForm) {
		Item item = itemService.findOne(id);
		itemService.delete(item);
		return "admin/home";
	}

	@GetMapping("orders")
	public String getorderList(Model model) {

		List<Order> orders = orderRepository.findAll();
		model.addAttribute("orders", orders);

		return "admin/orderList";

	}

	@GetMapping("orders/{orderId}")
	public String getorderItemDetail(@PathVariable("orderId") Long id, Model model) {
		Order order = orderRepository.findOne(id);
		List<OrderItem> orderItems = order.getOrderItems();

		model.addAttribute("orderItems", orderItems);

		return "admin/orderDetail";
	}

}
