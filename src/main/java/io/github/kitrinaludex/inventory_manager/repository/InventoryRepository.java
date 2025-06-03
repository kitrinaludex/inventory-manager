package io.github.kitrinaludex.inventory_manager.repository;

import io.github.kitrinaludex.inventory_manager.model.Inventory;
import io.github.kitrinaludex.inventory_manager.model.InventorySummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InventoryRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public long createInventory(String name,long userId) {

        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "INSERT INTO inventories(name,user_id) VALUES(?,?)";
                PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
                ps.setString(1, name);
                ps.setLong(2, userId);
                return ps;
            }
        }, key); //creates an inventory and adds its id to keyHolder

        jdbcTemplate.update("INSERT INTO inventory_authorities(user_id,inventory_id," +
                        "authority_level) VALUES(?,?,3)",
                userId,key.getKey().longValue());
        return key.getKey().longValue();
    }

    public Inventory getInventory(long id) {
        Inventory inventory =
                jdbcTemplate.queryForObject("SELECT * FROM inventories WHERE id = ?"
                        ,new InventoryMapper(), id);
        inventory.setItems(jdbcTemplate.query("SELECT * FROM items WHERE inventory_id = ?",
                new ItemMapper(),id));
        return inventory;
    }

    public List<InventorySummary> getInventoryList(long id) {
        String sql = "SELECT * FROM inventories WHERE user_id = ?";
        return jdbcTemplate.query(sql,(rs,rowNum) -> new InventorySummary(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getLong("user_id")
        )
        ,id);

    }

    public boolean exists(long inventoryId) {
        String sql = "SELECT EXISTS(SELECT 1 FROM inventories WHERE id = ?)";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, inventoryId));
    }
}
