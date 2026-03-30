package com.example.firstproject;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    private BookRepository br;

    // ================= ADD BOOK =================
    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody Books b) {
        br.save(b);
        return ResponseEntity.ok("Book Added Successfully");
    }

    // ================= VIEW ALL BOOKS =================
    @GetMapping("/all")
    public List<Books> getAllBooks() {
        return br.findAll();
    }

    // ================= SEARCH BOOK BY TITLE =================
   @GetMapping("/search")
public ResponseEntity<?> searchBook(@RequestParam String title) {
    if (title == null || title.trim().isEmpty()) {
        return ResponseEntity.badRequest().body("Please provide a book title");
    }

    // Search by title ignoring case
    List<Books> books = br.findByTitleContainingIgnoreCase(title);

    // Map to only needed fields
    List<Map<String, Object>> result = books.stream()
        .map(b -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", b.getId());
            m.put("title", b.getTitle());
            m.put("author", b.getAuthor());
            m.put("category", b.getCategory());
            m.put("price", b.getPrice());

            // ✅ ADD THIS LINE
            m.put("bookLink", b.getBookLink());

            return m;
        })
        .collect(Collectors.toList());

    return ResponseEntity.ok(result);
}
  // ================= DELETE BOOK =================
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestParam Integer id) {
        if (br.existsById(id)) {
            br.deleteById(id);
            return ResponseEntity.ok("Book Deleted Successfully");
        } else {
            return ResponseEntity.badRequest().body("Book with id " + id + " not found.");
        }
    }
    // UPDATE BOOK
@PutMapping("/update/{id}")
public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody Books updatedBook) {
    return br.findById(id)
            .map(book -> {
                // Update fields
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setCategory(updatedBook.getCategory());
                book.setPrice(updatedBook.getPrice());
                book.setBookLink(updatedBook.getBookLink());

                br.save(book); // save updated book
                return ResponseEntity.ok("Book updated successfully");
            })
            .orElseGet(() -> ResponseEntity.badRequest().body("Book with id " + id + " not found"));
}
}