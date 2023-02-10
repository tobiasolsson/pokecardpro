package com.pokecardpro.controller;

import com.pokecardpro.models.User;
import com.pokecardpro.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {

    UserService userService;
    @PostMapping("user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
/*
    @GetMapping("user")
    public User getSingleUser(@RequestBody String user) {
        return userService.getUserById(user);
    }

    @GetMapping("user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("user")
    public User updateSingleUser(User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("user")
    public String deleteSingleUser(String id) {
        return userService.deleteUser(id);
    }


 */

}