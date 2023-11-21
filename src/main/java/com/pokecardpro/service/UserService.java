package com.pokecardpro.service;

import com.pokecardpro.auth.AuthenticationService;
import com.pokecardpro.dto.UserDTO;
import com.pokecardpro.models.User;
import com.pokecardpro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationService authenticationService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User createUser(User user) {
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public UserDTO convertUserToUserDTO(User user) {
        return new UserDTO(user.getFirstName(),
                           user.getLastName(),
                           user.getEmail(),
                           user.getPhone(),
                           user.getStreet(),
                           user.getStreetNr(),
                           user.getCity(),
                           user.getZipCode());
    }

    @PreAuthorize("@authenticationService.getHasAccess(#id) or hasAuthority('ADMIN')")
    public ResponseEntity<UserDTO> getUserById(String id) {
        try {
            User currentUser = userRepository.findById(id).orElseThrow();
            UserDTO currentUserDTO = convertUserToUserDTO(currentUser);

            return ResponseEntity.ok().body(currentUserDTO);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


        }
    }

    // TODO: return list of UserDTO
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    // TODO: return 'user succesfully updated' eller UserDTO?
    @PreAuthorize("@authenticationService.getHasAccess(#id) or hasAuthority('ADMIN')")
    // Fyll i alla fält i postman vid uppdatering.
    public User updateUser(User user, String id) {
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    // TODO: Hantera om inte användare finns?
    @PreAuthorize("@authenticationService.getHasAccess(#id) or hasAuthority('ADMIN')")
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "User " + id + " has been deleted!";
    }

    // TODO: Hantera om inte användare finns?
    public String getUserEmail(String id) {
        return userRepository.findById(id).get().getEmail();
    }

    // TODO: Hantera om inte användare finns?
    public String getUserInformation(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        return "Email: " + user.getEmail() + ", phone_number: " + user.getPhone();
    }

    // TODO: Hantera om inte användare finns?
    public String getUserlocation(String id) {
        return userRepository.findById(id).orElseThrow().getCity();
    }

    public String sendUserAuctionWinMessage() {
        return "You have won auction";
    }

    public ResponseEntity<UserDTO> getCurrentUser() {

        try {
            // TODO: Break out to own function, used multiple places
            // get the userId from the securitycontext, this way we don't need to send and deal with user id on the frontend
            String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            User userAuth = userRepository.findByEmail(authenticatedUserEmail).orElseThrow(
                    () -> new NoSuchElementException("Something went wrong trying to fetch user object"));
            String userId = String.valueOf(userAuth.getId());


            User currentUser = userRepository.findById(userId).orElseThrow();
            UserDTO currentUserDTO = convertUserToUserDTO(currentUser);

            return ResponseEntity.ok().body(currentUserDTO);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

