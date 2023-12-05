package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface MyUserService extends UserDetailsService {
    List<User> findAll();
    User findById(int id);
    void save(User user);
    void deleteById(int id);
    @Override
    UserDetails loadUserByUsername(String username);
}
