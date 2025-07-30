package com.example.graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.graphql.model.Book;
import com.example.graphql.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping("path")
    public ResponseEntity<List<Book>> getMethodName() {
        return ResponseEntity.ok(service.getAllBooks());
    }

    @QueryMapping
    public List<Book> books() {
        return service.getAllBooks();
    }

    @MutationMapping
    public Book createBook(@Argument String title, @Argument String author) {
        return service.createBook(title, author);
    }

    @MutationMapping
    public Book updateBook(@Argument Long id, @Argument String title, @Argument String author) {
        return service.updateBook(id, title, author);
    }

    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        return service.deleteBook(id);
    }
}