package ru.kata.spring.boot_security.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.MyUserDetails;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private final UserRepository userRepository;


    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        List<User> allUsers = userRepository.findAll();
        model.addAttribute("allUsers", allUsers);
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userRepository.saveAndFlush(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/updateUser")
    public String updateUser(@PathVariable("id") int id, Model model){
    Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
        }
        return "update-user";
    }

    @PostMapping("/saveUpdatedUser")
    public String saveUpdatedUser(@ModelAttribute("user") User user) {
        userRepository.saveAndFlush(user);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/index")
    public String showFirstPage(){
        return "index";
    }

    @GetMapping("/user")
    public String showUserPage(Model model,Authentication authentication){
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        model.addAttribute("userInform",user.toString());
        return "user";
    }
}