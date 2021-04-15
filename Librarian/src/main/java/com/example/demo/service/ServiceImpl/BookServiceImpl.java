package com.example.demo.service.ServiceImpl;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = authorRepository.findById(authorId).orElseThrow(RuntimeException::new);
        Book book = new Book(name,category,author,availableCopies);
        bookRepository.save(book);
        return Optional.of(book);
    }


    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long author, Integer availableCopies) {
        Book book = bookRepository.findById(id).get();
        book.setName(name);
        book.setAvailableCopies(availableCopies);
        book.setCategory(category);

        Author realAuthor = authorRepository.findById(author).orElseThrow(RuntimeException::new);
        book.setAuthor(realAuthor);

        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
    bookRepository.deleteById(id);
    }

    @Override
    public void markAsTakenBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
    }
}
