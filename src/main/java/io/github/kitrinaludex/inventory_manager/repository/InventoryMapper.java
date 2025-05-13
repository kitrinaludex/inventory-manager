package io.github.kitrinaludex.inventory_manager.repository;

import io.github.kitrinaludex.inventory_manager.model.Inventory;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryMapper implements RowMapper<Inventory> {
    public InventoryMapper() {
    }

    public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Inventory((long) rs.getInt("id"),
                (long) rs.getInt("user_id"),rs.getString("name"));
    }


}
