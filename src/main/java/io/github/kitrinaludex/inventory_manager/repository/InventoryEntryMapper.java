package io.github.kitrinaludex.inventory_manager.repository;

import io.github.kitrinaludex.inventory_manager.model.InventoryEntry;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class InventoryEntryMapper implements RowMapper<InventoryEntry> {

    @Override
    public InventoryEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new InventoryEntry((long) rs.getInt("id"),rs.getString("name"),
                rs.getInt("quantity"));
    }
}
