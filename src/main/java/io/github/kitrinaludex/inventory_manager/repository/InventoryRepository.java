package io.github.kitrinaludex.inventory_manager.repository;

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
}
