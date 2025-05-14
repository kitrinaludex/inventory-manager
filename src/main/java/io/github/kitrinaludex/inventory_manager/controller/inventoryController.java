package io.github.kitrinaludex.inventory_manager.controller;

import io.github.kitrinaludex.inventory_manager.model.InventoryEntry;
import io.github.kitrinaludex.inventory_manager.repository.InventoryEntryRepository;
import io.github.kitrinaludex.inventory_manager.service.InventoryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class inventoryController {

    public final InventoryEntryService inventoryEntryService;
    @Autowired
    private InventoryEntryRepository inventoryEntryRepository;

    public inventoryController(InventoryEntryService inventoryEntryService) {
        this.inventoryEntryService = inventoryEntryService;
    }


    @GetMapping("/inventory/{id}")
    public ResponseEntity<List<InventoryEntry>> getInventoryEntries(@PathVariable long id) {
        return ResponseEntity.ok(inventoryEntryService.allEntries(id));
    }

    @PostMapping("/inventory/{id}")
    public ResponseEntity<InventoryEntry> createEntry(@PathVariable long id,
                                                      @RequestBody InventoryEntry inventoryEntry) {
        inventoryEntryService.createInventoryEntry(id,inventoryEntry);
        return ResponseEntity.created(URI.create("/inventory/item/" + id)).body(inventoryEntry);
    }

    @PutMapping("/inventory/{id}/item/{itemid}")
    public ResponseEntity<Void> updateItem(@PathVariable long id,
                                               @PathVariable long itemid,
                                               @RequestBody InventoryEntry inventoryEntry){
        inventoryEntryService.updateEntry(itemid,inventoryEntry);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inventory/{id}/item/{itemid}")
    public  ResponseEntity<Void> deleteItem(@PathVariable("itemid") long id){
        inventoryEntryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }


}


