package com.example.graphql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.graphql.model.Book;
import com.example.graphql.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository repo;

    public Book createBook(String title, String author) {
        return repo.save(new Book(title, author));
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public Book updateBook(Long id, String title, String author) {
        Book book = repo.findById(id).orElseThrow();
        book.setTitle(title);
        book.setAuthor(author);
        return repo.save(book);
    }

    public boolean deleteBook(Long id) {
        repo.deleteById(id);
        return true;
    }
}
