package com.example.firstproject;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")   // ✅ maps DB column
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "author_name")   // ✅ FIX HERE
    private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Double price;

    @Column(name = "book_link")
    private String bookLink;

    public Books() {}

    public Books(String title, String author, String category, Double price) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getBookLink() { return bookLink; }
    public void setBookLink(String bookLink) { this.bookLink = bookLink; }

}