package com.example.graphql.controller;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.graphql.model.Book;
import com.example.graphql.service.BookService;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping("path")
    public ResponseEntity<List<Book>> getMethodName() {
        return ResponseEntity.ok(service.getAllBooks());
    }

    @Timed(value = "graphql.books.query.performance.pj", description = "Time taken to fetch books")
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

    @Autowired
    private MeterRegistry meterRegistry;

    @GetMapping("time")
    public ResponseEntity<List<String>> logTiming() {
        Timer timer = meterRegistry.find("graphql.books.query.performance.pj").timer();
        if (timer != null) {
            String count = "Count: " + timer.count();
            String totalTime = "Total Time: " + timer.totalTime(TimeUnit.SECONDS);
            List<String> asList = Arrays.asList(count, totalTime);
            return ResponseEntity.ok(asList);
        }
        return ResponseEntity.notFound().build();
    }
}
