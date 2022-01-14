package com.example.tp2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tp2.repository.item.ItemRepository;
import com.example.tp2.domain.item.Item;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

	private final ItemRepository itemRepository;

	public void save(Item... items) {
		itemRepository.save(items);
	}

	public void delete(Item item) {
		itemRepository.delete(item);
	}

	public Item findOne(Long id) {
		return itemRepository.findOne(id);
	}

	public List<Item> findAll() {
		return itemRepository.getItemList();
	}

	public List<Item> findByCategory(String categoryName) {
		return itemRepository.findByCategory(categoryName);
	}
}
