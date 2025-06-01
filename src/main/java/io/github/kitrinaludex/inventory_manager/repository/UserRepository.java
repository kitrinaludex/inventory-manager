package io.github.kitrinaludex.inventory_manager.repository;

import io.github.kitrinaludex.inventory_manager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUser(long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
       return jdbcTemplate.queryForObject(sql,new UserMapper(),id);
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        return jdbcTemplate.queryForObject(sql,new UserMapper(),username);
    }

    public long addUser(User user) {
        String sql = "INSERT INTO USERS(user_name,password) VALUES(?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
