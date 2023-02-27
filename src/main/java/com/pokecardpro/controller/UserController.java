package com.pokecardpro.controller;

import com.pokecardpro.models.User;
import com.pokecardpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("createuser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("singleuser/{id}")
    public User getSingleUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("alluser")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("updateuser")
    public User updateSingleUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("deleteuser/{id}")
    public String deleteSingleUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @GetMapping("userid/{id}")
    public String getUserEmail(@PathVariable String id) {
        return userService.getUserEmail(id);
    }

    @GetMapping("userlocation/{id}")
    public String getUserLocation(@PathVariable String id) {return userService.getUserlocation(id);}

    @GetMapping("usercontact/{id}")
    public String getUserInformation(@PathVariable String id) {
        return userService.getUserInformation(id);
    }

    @GetMapping("auctionwinmessage")
    public String getAuctionWinMessage() {
        return userService.sendUserAuctionWinMessage();
    }
}