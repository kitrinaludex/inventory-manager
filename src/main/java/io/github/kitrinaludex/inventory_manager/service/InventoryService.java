package io.github.kitrinaludex.inventory_manager.service;

import io.github.kitrinaludex.inventory_manager.model.InventoryEntry;
import io.github.kitrinaludex.inventory_manager.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public List<InventoryEntry> allEntries(long id) {
        return inventoryRepository.getInventoryContent(id);
    }

    public void createInventoryEntry(long id, InventoryEntry inventoryEntry) {
        inventoryRepository.createInventoryEntry(id,inventoryEntry);
    }

    public void updateEntryName(long id,String name) {
      inventoryRepository.updateEntryName(id,name);
    }

    public void updateEntryQuantity(long id,int quantity) {
        inventoryRepository.updateEntryQuantity(id,quantity);
    }

    public void deleteItem(long id) {
        inventoryRepository.deleteItem(id);
    }
}
