package io.github.kitrinaludex.inventory_manager.controller;

import io.github.kitrinaludex.inventory_manager.model.Item;
import io.github.kitrinaludex.inventory_manager.repository.InventoryItemRepository;
import io.github.kitrinaludex.inventory_manager.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class inventoryController {

    public final InventoryService inventoryEntryService;
    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    public inventoryController(InventoryService inventoryEntryService) {
        this.inventoryEntryService = inventoryEntryService;
    }


    @GetMapping("/inventory/{id}")
    public ResponseEntity<List<Item>> getItems(@PathVariable long id) {
        return ResponseEntity.ok(inventoryEntryService.allItems(id));
    }

    @PostMapping("/inventory/{id}")
    public ResponseEntity<Item> createEntry(@PathVariable long id,
                                            @RequestBody Item item) {
        inventoryEntryService.createItem(id, item);
        return ResponseEntity.created(URI.create("/inventory/item/" + id)).body(item);
    }

    @PutMapping("/inventory/{id}/item/{itemid}")
    public ResponseEntity<Void> updateItem(@PathVariable long id,
                                               @PathVariable long itemid,
                                               @RequestBody Item item){
        inventoryEntryService.updateItem(itemid, item);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inventory/{id}/item/{itemid}")
    public  ResponseEntity<Void> deleteItem(@PathVariable("itemid") long id){
        inventoryEntryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }


}


