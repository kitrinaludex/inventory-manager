package io.github.kitrinaludex.inventory_manager.controller;

import io.github.kitrinaludex.inventory_manager.dto.InventoryEntryDto;
import io.github.kitrinaludex.inventory_manager.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.ResponseCache;
import java.util.List;

@RestController
public class inventoryController {

    public final InventoryService inventoryService;


    public inventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory")
    public ResponseEntity<List<InventoryEntryDto>> allRecords() {
        return ResponseEntity.ok(inventoryService.allRecords());
    }

}
