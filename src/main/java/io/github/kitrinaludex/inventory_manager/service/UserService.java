package io.github.kitrinaludex.inventory_manager.service;

import io.github.kitrinaludex.inventory_manager.model.User;
import io.github.kitrinaludex.inventory_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUser(long id) {
       return userRepository.getUser(id);
    }

    public long addUser(User user) {
        return userRepository.addUser(user);
    }
}
