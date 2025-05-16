package io.github.kitrinaludex.inventory_manager.repository;


import io.github.kitrinaludex.inventory_manager.model.Inventory;
import io.github.kitrinaludex.inventory_manager.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InventoryItemRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public Inventory getInventory(long id) {
        String sql = "SELECT * FROM inventories WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,new InventoryMapper(),id);
    }

    public List<Item> getInventoryContent(long id) {
        String sql = "SELECT * FROM items WHERE inventory_id = ?";
        return jdbcTemplate.query(sql,new ItemMapper(),id);
    }

    public Item getItemById(long id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,new ItemMapper(),id);
    }

    public void updateItemName(long id,String name) {
        jdbcTemplate.update("UPDATE items SET name = ? WHERE id = ?",name,id);
    }

    public void updateItemQuantity(long id, int quantity) {
        jdbcTemplate.update("UPDATE items SET quantity = ? WHERE id = ?",quantity,id);
    }


    public void createItem(long id, Item item) {
        jdbcTemplate.update("INSERT INTO items(inventory_id,name,quantity) VALUES(?,?,?)",
                id,
                item.getName(),
                item.getQuantity());
    }


    public void deleteItem(long id) {
        jdbcTemplate.update("DELETE FROM items WHERE id = ?",id);
    }

    public void updateItem(long id, Item item) {
        jdbcTemplate.update("UPDATE items SET name = ?, quantity = ? WHERE id = ?",
                item.getName(), item.getQuantity(),id);
    }
}
