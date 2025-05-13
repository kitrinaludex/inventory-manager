package io.github.kitrinaludex.inventory_manager.repository;


import io.github.kitrinaludex.inventory_manager.model.InventoryEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public InventoryRepository() {
    }

    public void add() {

        jdbcTemplate.execute("INSERT INTO test VALUES(420)");
    }

}
