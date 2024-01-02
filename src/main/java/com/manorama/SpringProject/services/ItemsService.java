package com.manorama.SpringProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manorama.SpringProject.entities.Items;
import com.manorama.SpringProject.repositories.ItemsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemsService {
	private final ItemsRepository itemsRepository;

	@Autowired
	public ItemsService(ItemsRepository itemsRepository) {
		this.itemsRepository = itemsRepository;
	}

	public List<Items> getAllItems() {
		return itemsRepository.findAll();
	}

	public ResponseEntity addItem(Items item) {
		if (item.isIncomplete()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		try {
			Items savedItem = itemsRepository.save(item);
			return ResponseEntity.ok(savedItem);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.internalServerError().build();
			
		}
	}

	public void addItems(List<Items> items) {
		itemsRepository.saveAll(items);
	}

	public void deleteItem(Long id) {
		itemsRepository.deleteById(id);
	}

	public Optional<Items> getItemsById(Long id) {
		return itemsRepository.findById(id);
	}

	public Items updateItem(Items items) {
		Optional<Items> fetchedItem = itemsRepository.findById(items.getId());
		if (fetchedItem.isPresent()) {
			Items item = fetchedItem.get();
			items.setId(item.getId());
			Items updatedItem = itemsRepository.save(items);
			return updatedItem;
		}
		return null;
	}

	public List<Items> getItemsByCategory(String category) {
		return itemsRepository.findAllByCategory(category);
	}
}
