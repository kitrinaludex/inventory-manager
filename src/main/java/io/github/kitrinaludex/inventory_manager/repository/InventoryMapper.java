package io.github.kitrinaludex.inventory_manager.repository;

import io.github.kitrinaludex.inventory_manager.model.Inventory;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InventoryMapper implements RowMapper<Inventory> {
    public InventoryMapper() {
    }

    public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Inventory((long) rs.getInt("id"),rs.getString("name"),
                (long) rs.getInt("user_id"), List.of());
    }


}
