package com.example.firstproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") // allow frontend
public class UsersController {

    @Autowired
    private UsersService usersService;

    // Signup
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Users user) {
        try {
            Users newUser = usersService.registerUser(
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
            );
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

     // Signin using username + password
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Users user) {
        try {
            Users loggedInUser = usersService.loginUser(
                    user.getUsername().trim(),
                    user.getPassword().trim()
            );
            return ResponseEntity.ok(loggedInUser); // return user object if success
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // return error
        }
    }
}