package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Category;


import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    void deleteById(Long id);
    Optional<Book> save(String name, Category category, Long author,Integer availableCopies);
    Optional<Book> edit(Long id, String name, Category category, Long author, Integer availableCopies);
    void markAsTakenBook(Long id);
}
