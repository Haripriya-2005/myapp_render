package com.example.firstproject;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books, Integer> {

    List<Books> findByTitleContainingIgnoreCase(String title);

}