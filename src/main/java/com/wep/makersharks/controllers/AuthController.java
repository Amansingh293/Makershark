package com.wep.makersharks.controllers;


import com.wep.makersharks.DTO.UserSessionDTO;
import com.wep.makersharks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UserService userServices;

    @Autowired
    public AuthController(UserService userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserSessionDTO user) {

        try {
            user.validateDTO();

            // Check if user exists
            if (!userServices.UserExists(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // Attempt login
            UserSessionDTO loggedInUser = userServices.login(user);
            if (loggedInUser != null) {
                return ResponseEntity.ok(loggedInUser);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }

        } catch (Exception e) {
            // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> signup(@RequestBody UserSessionDTO user) {
        try {
            user.validateDTO();

            // Check if user already exists
            if (userServices.UserExists(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("A user with this email already exists");
            }

            // Attempt registration
            UserSessionDTO registeredUser = userServices.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);

        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request parameters: " + e.getMessage());
        }
        catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration");
        }
    }


    // Helper method to validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) return false;
        return pattern.matcher(email).matches();
    }

}
