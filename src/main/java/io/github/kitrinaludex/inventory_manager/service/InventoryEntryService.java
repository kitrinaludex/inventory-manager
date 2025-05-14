package io.github.kitrinaludex.inventory_manager.service;

import io.github.kitrinaludex.inventory_manager.model.InventoryEntry;
import io.github.kitrinaludex.inventory_manager.repository.InventoryEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryEntryService {

    @Autowired
    InventoryEntryRepository inventoryEntryRepository;

    public List<InventoryEntry> allEntries(long id) {
        return inventoryEntryRepository.getInventoryContent(id);
    }

    public void createInventoryEntry(long id, InventoryEntry inventoryEntry) {
        inventoryEntryRepository.createInventoryEntry(id,inventoryEntry);
    }

    public void updateEntry(long id, InventoryEntry inventoryEntry) {
        inventoryEntryRepository.updateEntry(id,inventoryEntry);
    }

    public void deleteItem(long id) {
        inventoryEntryRepository.deleteItem(id);
    }


}
