package io.github.kitrinaludex.inventory_manager.repository;

import io.github.kitrinaludex.inventory_manager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUser(long id) {
        String sql = "Select * FROM users WHERE id = ?";
       return jdbcTemplate.queryForObject(sql,new UserMapper(),id);
    }
}
