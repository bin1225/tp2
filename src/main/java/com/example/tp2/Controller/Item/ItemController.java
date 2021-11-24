package com.example.tp2.Controller.Item;

import com.example.tp2.Service.ItemService;
import com.example.tp2.domain.item.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostConstruct
    public void addData(){
        for(int i =0;i<8;i++) {
            Item item = new Item("item",i);

            itemService.save(item);
        }
        Item item1 = new Item("new top1",30000);
        item1.setStockQuantity(100);


        log.info("아이템 저장 성공");

    }

    @GetMapping("/main")
    public String shop_main(Model model){
        List<Item> items = itemService.findAll();
        model.addAttribute("items",items);

        return "main";
    }

    @GetMapping("item/form/{itemId}")
    public String itemForm(@PathVariable Long itemId, Model model){

        Item item = itemService.findOne(itemId);
        model.addAttribute("item",item);

        return "itemForm";
    }

    @GetMapping("item/form/main")
    public String item(){
        log.info("아이템 화면");
        return "itemForm";
    }
}
