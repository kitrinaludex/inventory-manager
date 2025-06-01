package io.github.kitrinaludex.inventory_manager.repository;


import io.github.kitrinaludex.inventory_manager.model.Inventory;
import io.github.kitrinaludex.inventory_manager.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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


    public long createItem(long id, Item item) {
        String sql = "INSERT INTO items(inventory_id,name,quantity) VALUES(?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setLong(1, id);
                ps.setString(2, item.getName());
                ps.setLong(3,item.getQuantity());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }


    public void deleteItem(long id) {
        jdbcTemplate.update("DELETE FROM items WHERE id = ?",id);
    }

    public void updateItem(long id, Item item) {
        jdbcTemplate.update("UPDATE items SET name = ?, quantity = ? WHERE id = ?",
                item.getName(), item.getQuantity(),id);
    }
}
