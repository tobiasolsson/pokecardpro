package com.pokecardpro.service;

import com.pokecardpro.models.User;
import com.pokecardpro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User createUser(User user) {
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User getUserById(String id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    // Fyll i alla fält i postman vid uppdatering.
    public User updateUser(User user, String id) {
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "User " + id + " has been deleted!";
    }

    public String getUserEmail(String id) {
        return userRepository.findById(id).get().getEmail();
    }

    public String getUserInformation(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        return "Email: " + user.getEmail() + ", phone_number: " + user.getPhone();
    }

    public String getUserlocation(String id) {
        return userRepository.findById(id).orElseThrow().getCity();
    }

    public String sendUserAuctionWinMessage() {
        return "You have won auction";
    }
}

