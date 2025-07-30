package com.example.graphql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Book {
    
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String author;

    public Book() {
        // Default constructor for JPA
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

}
