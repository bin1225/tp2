package com.example.tp2.Controller.Admin;


import com.example.tp2.Repository.Member.MemberRepository;
import com.example.tp2.Repository.Order.OrderRepository;
import com.example.tp2.Service.ItemService;
import com.example.tp2.domain.Member;
import com.example.tp2.domain.Order;
import com.example.tp2.domain.item.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {


    private final MemberRepository memberRepository;
    private final ItemService itemService;
    private final OrderRepository orderRepository;

    @GetMapping("/admin/main")
    public String main(){
        return "admin/home";
    }

    @GetMapping("/admin/members")
    public String memberList(Model model){

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
    public String items(Model model) {

        List<Item> itemList = itemService.findAll();
        model.addAttribute("items", itemList);

        return "admin/items/list";
    }

    @ModelAttribute("categories")
    public List<String> category(){
        List<String> categories = new ArrayList<>();
        categories.add("top");
        categories.add("trouser");
        categories.add("shoe");

        return categories;
    }

    @PostMapping("/items/new")
    public String regist(@ModelAttribute ItemForm itemForm){

        Item item = new Item(itemForm.getName(),itemForm.getPrice(),itemForm.getCategory());
        if(itemForm.getImgUrl()==null) {
            item.setImgUrl("https://image.thehyundai.com/static/image/sect/hnm/cpnt/hnmmain1344420020211103141541.png");
        }else {
            item.setImgUrl(itemForm.getImgUrl());
        }
        itemService.save(item);


        return "admin/home";
    }

    @GetMapping("items/{itemId}/edit")
    public String editForm(@ModelAttribute("form")ItemForm itemForm, Model model
            ,@PathVariable("itemId") Long id){
        Item item = itemService.findOne(id);
        itemForm.setName(item.getName());
        itemForm.setPrice(item.getPrice());
        itemForm.setStockQuantity(item.getStockQuantity());
        model.addAttribute("form",itemForm);
        return "admin/items/editForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long id,@ModelAttribute("form")ItemForm itemForm)
    {
        Item item = itemService.findOne(id);
        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());
        item.setStockQuantity(itemForm.getStockQuantity());
        item.setCategory(itemForm.getCategory());
        itemService.save(item);
        return "admin/home";
    }
    @PostMapping("items/{itemId}/delete")
    public String delete(@PathVariable("itemId") Long id,@ModelAttribute("form")ItemForm itemForm)
    {
        Item item = itemService.findOne(id);
        itemService.delete(item);
        return "admin/home";
    }



    @GetMapping("orders")
    public String orderList(Model model){

        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders",orders);


        return "admin/orderList";

    }



}
