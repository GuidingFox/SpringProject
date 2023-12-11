package com.manorama.SpringProject.Items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
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
    public void addItems(@RequestBody Items item) {
        itemsService.addItem(item);
    }

    @DeleteMapping
    public void deleteItems(@RequestParam Long id) {
        itemsService.deleteItem(id);
    }
}
