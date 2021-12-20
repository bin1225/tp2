package com.example.tp2.Controller.Item;

import com.example.tp2.Service.ItemService;
import com.example.tp2.domain.item.Category;
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
        Category top = new Category("top");
        Category trouser = new Category("trouser");
        Category shoe = new Category("shoe");


        Item item1 = new Item("new top1",300000,"top");
        item1.setStockQuantity(100);
        item1.setImgUrl("https://image.thehyundai.com/static/3/0/8/38/A1/hnm40A1388038_02_1001878_001_001_720.jpg");
        Item item2 = new Item("new top2",100000,"top");
        item2.setStockQuantity(100);
        item2.setImgUrl("https://image.thehyundai.com/static/9/6/7/67/A0/hnm40A0677699_02_0537856_004_001_720.jpg");
        Item item3 = new Item("new top3",60000,"top");
        item3.setStockQuantity(100);
        item3.setImgUrl("https://image.thehyundai.com/static/5/1/8/39/A1/hnm40A1398158_02_0908863_007_001_720.jpg");
        Item item4 = new Item("new trouser1",22000,"trouser");
        item4.setStockQuantity(100);
        item4.setImgUrl("https://image.thehyundai.com/static/7/5/5/40/A1/hnm40A1405576_02_0674976_007_001_400.jpg");
        Item item5 = new Item("new trouser2",18000,"trouser");
        item5.setStockQuantity(100);
        item5.setImgUrl("https://image.thehyundai.com/static/2/3/7/37/A1/hnm40A1377321_02_1003340_002_001_400.jpg");
        Item item6 = new Item("new trouser3",15000,"trouser");
        item6.setStockQuantity(100);
        item6.setImgUrl("https://image.thehyundai.com/static/2/9/2/41/A1/hnm40A1412923_02_1023485_001_001_400.jpg");
        Item item7 = new Item("new shoe1",10000,"shoe");
        item7.setStockQuantity(100);
        item7.setImgUrl("https://image.thehyundai.com/static/6/3/7/35/A1/hnm40A1357362_02_0849499_008_002_400.jpg");
        Item item8= new Item("new shoe2",23000,"shoe");
        item8.setStockQuantity(100);
        item8.setImgUrl("https://image.thehyundai.com/static/5/5/3/41/A1/hnm40A1413555_02_1016412_001_003_400.jpg");

        itemService.save(item1,item2,item3,item4,item5,item6,item7,item8);

        Item ite1 = new Item("premium top1",300000,"top");
        ite1.setStockQuantity(100);
        ite1.setImgUrl("https://image.thehyundai.com/static/3/0/8/38/A1/hnm40A1388038_02_1001878_001_001_720.jpg");
        Item ite2 = new Item("premium top2",100000,"top");
        ite2.setStockQuantity(100);
        ite2.setImgUrl("https://image.thehyundai.com/static/9/6/7/67/A0/hnm40A0677699_02_0537856_004_001_720.jpg");
        Item ite3 = new Item("premium top3",60000,"top");
        ite3.setStockQuantity(100);
        ite3.setImgUrl("https://image.thehyundai.com/static/5/1/8/39/A1/hnm40A1398158_02_0908863_007_001_720.jpg");
        Item ite4 = new Item("premium trouser1",22000,"trouser");
        ite4.setStockQuantity(100);
        ite4.setImgUrl("https://image.thehyundai.com/static/7/5/5/40/A1/hnm40A1405576_02_0674976_007_001_400.jpg");
        Item ite5 = new Item("premium trouser2",18000,"trouser");
        ite5.setStockQuantity(100);
        ite5.setImgUrl("https://image.thehyundai.com/static/2/3/7/37/A1/hnm40A1377321_02_1003340_002_001_400.jpg");
        Item ite6 = new Item("premium trouser3",15000,"trouser");
        ite6.setStockQuantity(100);
        ite6.setImgUrl("https://image.thehyundai.com/static/2/9/2/41/A1/hnm40A1412923_02_1023485_001_001_400.jpg");
        Item ite7 = new Item("premium shoe1",10000,"shoe");
        ite7.setStockQuantity(100);
        ite7.setImgUrl("https://image.thehyundai.com/static/6/3/7/35/A1/hnm40A1357362_02_0849499_008_002_400.jpg");
        Item ite8= new Item("premium shoe2",23000,"shoe");
        ite8.setStockQuantity(100);
        ite8.setImgUrl("https://image.thehyundai.com/static/5/5/3/41/A1/hnm40A1413555_02_1016412_001_003_400.jpg");

        itemService.save(ite1,ite2,ite3,ite4,ite5,ite6,ite7,ite8);
        log.info("아이템 저장 성공");

    }

    @GetMapping("/main")
    public String shop_main(Model model){
        List<Item> items = itemService.findAll();
        model.addAttribute("items",items);

        return "main";
    }
    @GetMapping("/category/{categoryName}")
    public String main_cateogory(@PathVariable("categoryName") String cateogoryName,Model model){
        log.info("-------------------------------category_---------------------------");
        List<Item> items = itemService.findByCategory(cateogoryName);
        model.addAttribute("category",cateogoryName);
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
