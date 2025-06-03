package io.github.kitrinaludex.inventory_manager.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class PermissionRepository {
    private final JdbcTemplate jdbcTemplate;

    public PermissionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setRole(Long userId,Long inventoryId,String role) {
        String sql = "INSERT INTO inventory_permissions(user_id,inventory_id,role) VALUES(?,?,?)" +
                "ON CONFLICT (role) DO NOTHING";
    }


    public boolean hasPermission(long userId, long inventoryId,String requiredRole) {
        String sql = "SELECT role FROM inventory_permissions WHERE user_id = ? AND inventory_id = ?";

        try {
            String userRole = jdbcTemplate.queryForObject(sql, String.class, userId, inventoryId);
            Map<String, Integer> rolePower = Map.of(
                    "OWNER", 3,
                    "EDITOR", 2,
                    "VIEWER", 1
            );

            return rolePower.getOrDefault(userRole,0) >=
                    rolePower.getOrDefault(requiredRole,0);
        }catch (EmptyResultDataAccessException e) {
            return false;
        }

    }
}
