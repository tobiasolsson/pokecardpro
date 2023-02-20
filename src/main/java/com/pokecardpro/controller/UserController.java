package com.pokecardpro.controller;

import com.pokecardpro.models.User;
import com.pokecardpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

}