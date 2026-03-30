package com.example.firstproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    // Signup
    public Users registerUser(String username, String email, String password) throws Exception {
        if (usersRepository.existsByEmail(email)) {
            throw new Exception("Email already registered!");
        }
        if (usersRepository.existsByUsername(username)) {
            throw new Exception("Username already taken!");
        }
        Users user = new Users(username, email, password);
        return usersRepository.save(user);
    }

    // Login using username + password
    public Users loginUser(String username, String password) throws Exception {
        Users user = usersRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new Exception("Invalid username or password");
        }
        return user;
    }
}