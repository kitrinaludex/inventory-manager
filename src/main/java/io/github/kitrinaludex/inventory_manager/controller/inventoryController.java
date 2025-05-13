package io.github.kitrinaludex.inventory_manager.controller;

import io.github.kitrinaludex.inventory_manager.dto.InventoryEntryDto;
import io.github.kitrinaludex.inventory_manager.repository.InventoryRepository;
import io.github.kitrinaludex.inventory_manager.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.ResponseCache;
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
    public ResponseEntity<List<InventoryEntryDto>> allEntries() {
        return ResponseEntity.ok(inventoryService.allEntries());
    }

    @GetMapping("/inventoryadd")
    public ResponseEntity<String> add() {
        inventoryRepository.add();
        return ResponseEntity.ok("adsf");
    }


    }


