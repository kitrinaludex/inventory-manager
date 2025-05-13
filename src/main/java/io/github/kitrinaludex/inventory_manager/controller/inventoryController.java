package io.github.kitrinaludex.inventory_manager.controller;

import io.github.kitrinaludex.inventory_manager.model.InventoryEntry;
import io.github.kitrinaludex.inventory_manager.repository.InventoryRepository;
import io.github.kitrinaludex.inventory_manager.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.ResponseCache;
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


    @GetMapping("/inventory")
    public ResponseEntity<List<InventoryEntry>> add(@RequestParam long id) {
        return ResponseEntity.ok(inventoryService.allEntries(id));
    }

    @PostMapping("/inventory")
    public ResponseEntity<InventoryEntry> createEntry(@RequestParam long id,
                                                      @RequestBody InventoryEntry inventoryEntry) {
        inventoryService.createInventoryEntry(id,inventoryEntry);
        return ResponseEntity.created(URI.create("/inventory?id=" + id)).body(inventoryEntry);
    }

    @PatchMapping("/inventory/updatename")
    public ResponseEntity<Void> updateItemName(@RequestParam("id") long id,
            @RequestParam("name") String name){
        inventoryService.updateEntryName(id,name);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/inventory/updatequantity")
    public ResponseEntity<Void> updateItemQuantity(@RequestParam long id,
                                                   @RequestParam int quantity){
        inventoryService.updateEntryQuantity(id,quantity);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inventory/delete")
    public  ResponseEntity<Void> deleteItem(@RequestParam long id){
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }



}


