package io.github.kitrinaludex.inventory_manager.service;

import io.github.kitrinaludex.inventory_manager.model.Item;
import io.github.kitrinaludex.inventory_manager.repository.InventoryItemRepository;
import io.github.kitrinaludex.inventory_manager.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    InventoryItemRepository inventoryItemRepository;
    @Autowired
    InventoryRepository inventoryRepository;

    public List<Item> allItems(long id) {
        return inventoryItemRepository.getInventoryContent(id);
    }

    public void createItem(long id, Item item) {
        inventoryItemRepository.createItem(id, item);
    }

    public void updateItem(long id, Item item) {
        inventoryItemRepository.updateItem(id, item);
    }

    public void deleteItem(long id) {
        inventoryItemRepository.deleteItem(id);
    }


    public void createInventory(String name,long userId) {
        inventoryRepository.createInventory(name,userId);
    }
}
