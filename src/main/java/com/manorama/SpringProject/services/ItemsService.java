package com.manorama.SpringProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manorama.SpringProject.models.Items;
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

	public void addItem(Items item) {
		itemsRepository.save(item);
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

	public void updateItem(Items items) {
		Optional<Items> fetchedItem = itemsRepository.findById(items.getId());
		if (fetchedItem.isPresent()) {
			Items item = fetchedItem.get();
			items.setId(item.getId());
			itemsRepository.save(items);
		}
	}
}
