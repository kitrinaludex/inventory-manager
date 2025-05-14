package io.github.kitrinaludex.inventory_manager.repository;


import io.github.kitrinaludex.inventory_manager.model.Inventory;
import io.github.kitrinaludex.inventory_manager.model.InventoryEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InventoryEntryRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public Inventory getInventory(long id) {
        String sql = "SELECT * FROM inventories WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,new InventoryMapper(),id);
    }

    public List<InventoryEntry> getInventoryContent(long id) {
        String sql = "SELECT * FROM items WHERE inventory_id = ?";
        return jdbcTemplate.query(sql,new InventoryEntryMapper(),id);
    }

    public InventoryEntry getEntryById(long id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,new InventoryEntryMapper(),id);
    }
    public void add(Inventory inventory) {
        jdbcTemplate.update("INSERT INTO inventories VALUES(?)",inventory.getName());
    }

    public void updateEntryName(long id,String name) {
        jdbcTemplate.update("UPDATE items SET name = ? WHERE id = ?",name,id);
    }

    public void updateEntryQuantity(long id, int quantity) {
        jdbcTemplate.update("UPDATE items SET quantity = ? WHERE id = ?",quantity,id);
    }


    public void createInventoryEntry(long id, InventoryEntry inventoryEntry) {
        jdbcTemplate.update("INSERT INTO items(inventory_id,name,quantity) VALUES(?,?,?)",
                id,
                inventoryEntry.getName(),
                inventoryEntry.getQuantity());
    }


    public void deleteItem(long id) {
        jdbcTemplate.update("DELETE FROM items WHERE id = ?",id);
    }

    public void updateEntry(long id, InventoryEntry inventoryEntry) {
        jdbcTemplate.update("UPDATE items SET name = ?, quantity = ? WHERE id = ?",
                inventoryEntry.getName(),inventoryEntry.getQuantity(),id);
    }
}
