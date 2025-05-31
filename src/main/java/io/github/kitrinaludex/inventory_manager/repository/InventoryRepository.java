package io.github.kitrinaludex.inventory_manager.repository;

import io.github.kitrinaludex.inventory_manager.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createInventory(String name,long userId) {
        jdbcTemplate.update("INSERT INTO inventories(name,user_id) VALUES(?,?)",
                name,userId);
    }

    public Inventory getInventory(long id) {
        Inventory inventory =
                jdbcTemplate.queryForObject("SELECT * FROM inventories WHERE id = ?"
                        ,new InventoryMapper(), id);
        inventory.setItems(jdbcTemplate.query("SELECT * FROM items WHERE inventory_id = ?",
                new ItemMapper(),id));
        return inventory;
    }
}
