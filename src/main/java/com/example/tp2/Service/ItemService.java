package com.example.tp2.Service;

import com.example.tp2.Repository.Item.ItemRepository;
import com.example.tp2.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {


    private final ItemRepository itemRepository;



    public Long save(Item item){
        itemRepository.save(item);
        return item.getId();
    }

    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }

    public List<Item> findAll(){
        return itemRepository.getItemList();
    }

}
