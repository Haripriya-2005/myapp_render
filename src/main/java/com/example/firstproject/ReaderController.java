package com.example.firstproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReaderController {

    @Autowired
    ReaderRepository rr;

    // REGISTER
    @PostMapping("/reader/register")
    public ResponseEntity<String> register(@RequestBody Reader r) {

        Reader er = rr.findByName(r.getName());

        if (er != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Reader already exists");
        }

        rr.save(r);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Reader registered successfully");
    }

    // LOGIN
    @PostMapping("/reader/login")
    public ResponseEntity<String> login(@RequestBody Reader r) {

        Reader er = rr.findByName(r.getName());

        if (er != null && er.getPassword().equals(r.getPassword())) {
            return ResponseEntity.ok("Login Successful");
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid Credentials");
    }

    // VIEW ALL READERS
    @GetMapping("/reader/all")
    public List<Reader> getAllReaders() {
        return rr.findAll();
    }
}