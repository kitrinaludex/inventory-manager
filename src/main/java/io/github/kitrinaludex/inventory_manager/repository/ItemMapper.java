package io.github.kitrinaludex.inventory_manager.repository;

import io.github.kitrinaludex.inventory_manager.model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ItemMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Item((long) rs.getInt("id"),rs.getString("name"),
                rs.getInt("quantity"));
    }
}
