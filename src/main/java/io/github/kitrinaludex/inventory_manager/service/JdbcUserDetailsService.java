package io.github.kitrinaludex.inventory_manager.service;

import io.github.kitrinaludex.inventory_manager.model.User;
import io.github.kitrinaludex.inventory_manager.repository.UserRepository;
import io.github.kitrinaludex.inventory_manager.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JdbcUserDetailsService implements UserDetailsService {

    public JdbcUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        return new SecurityUser(user);
    }
}
