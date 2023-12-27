package com.manorama.SpringProject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manorama.SpringProject.models.Items;
import com.manorama.SpringProject.services.ItemsService;

@RestController
@RequestMapping(path = "/api/items")
public class ItemsController {
	private final ItemsService itemsService;

	@Autowired
	public ItemsController(ItemsService itemsService) {
		this.itemsService = itemsService;
	}

	@GetMapping
	public List<Items> getAllItems() {
		return itemsService.getAllItems();
	}

	@PostMapping
	public void addItem(@RequestBody Items item) {
		itemsService.addItem(item);
	}

	@PostMapping("/add")
	public void addItems(@RequestBody List<Items> items) {
		itemsService.addItems(items);
	}

	@DeleteMapping
	public void deleteItems(@RequestParam Long id) {
		itemsService.deleteItem(id);
	}

	@PutMapping
	public void updateItems(@RequestBody Items item) {
		itemsService.updateItem(item);
	}

	@GetMapping("/user/{id}")
	public Optional<Items> getItemById(@PathVariable Long id) {
		return itemsService.getItemsById(id);
	}

}
