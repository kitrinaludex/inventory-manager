package io.github.kitrinaludex.inventory_manager.controller;

import io.github.kitrinaludex.inventory_manager.model.InventoryEntry;
import io.github.kitrinaludex.inventory_manager.repository.InventoryRepository;
import io.github.kitrinaludex.inventory_manager.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class inventoryController {

    public final InventoryService inventoryService;
    @Autowired
    private InventoryRepository inventoryRepository;

    public inventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @GetMapping("/inventory/{id}")
    public ResponseEntity<List<InventoryEntry>> showInventoryEntries(@PathVariable long id) {
        return ResponseEntity.ok(inventoryService.allEntries(id));
    }

    @PostMapping("/inventory/{id}")
    public ResponseEntity<InventoryEntry> createEntry(@PathVariable long id,
                                                      @RequestBody InventoryEntry inventoryEntry) {
        inventoryService.createInventoryEntry(id,inventoryEntry);
        return ResponseEntity.created(URI.create("/inventory?id=" + id)).body(inventoryEntry);
    }

    @PatchMapping("/inventory/{id}/item/{itemid}")
    public ResponseEntity<Void> updateItem(@PathVariable long id,
                                               @PathVariable long itemid,
                                               @RequestBody InventoryEntry inventoryEntry){
        inventoryService.updateEntryName(id,inventoryEntry);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/inventory/{itemid}")
    public  ResponseEntity<Void> deleteItem(@PathVariable long id){
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }



}


