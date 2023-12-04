package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Component
public interface UserDAO {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUserById(int id);
    public void updateUser(User user);
    public void delete(int id);
    public User findByUsername (String username);
}
