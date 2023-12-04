package ru.kata.spring.boot_security.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.MyUserService;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private final MyUserService userService;


    @Autowired
    public UsersController(MyUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.findAll();
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
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/updateUser")
    public String updateUser(@PathVariable("id") int id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PutMapping("/saveUpdatedUser")
    public String saveUpdatedUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/index")
    public String showFirstPage(){
        return "index";
    }

    @GetMapping("/user")
    public String showUserPage(Model model,Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("userInform",user.toString());
        return "user";
    }
}