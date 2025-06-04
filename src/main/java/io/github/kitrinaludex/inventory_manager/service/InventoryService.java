package io.github.kitrinaludex.inventory_manager.service;

import io.github.kitrinaludex.inventory_manager.model.Inventory;
import io.github.kitrinaludex.inventory_manager.model.InventorySummary;
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

    public boolean exists(long inventoryId){
        return inventoryRepository.exists(inventoryId);
    }

    public List<Item> allItems(long id) {
        return inventoryItemRepository.getInventoryContent(id);
    }

    public long createItem(long id, Item item) {
        return inventoryItemRepository.createItem(id, item);
    }

    public void updateItem(long id, Item item) {
        inventoryItemRepository.updateItem(id, item);
    }

    public void deleteItem(long id) {
        inventoryItemRepository.deleteItem(id);
    }


    public long createInventory(String name,long userId) {
        return inventoryRepository.createInventory(name,userId);

    }

    public Inventory getInventory(long id) {
        return inventoryRepository.getInventory(id);
    }

    public List<InventorySummary> getInventoryList(long id) {
        return inventoryRepository.getInventoryList(id);
    }

    public void updateInventory(long id, Inventory inventory) {
        inventoryRepository.updateInventory(id,inventory);
    }

    public void deleteInventory(long id) {
        inventoryRepository.deleteInventory(id);
    }

    public Item getItem(long itemId) {
    return inventoryItemRepository.getItemById(itemId);
    }
}
