package com.example.firstproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {

    Reader findByName(String name);

}