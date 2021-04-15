package com.example.demo.web;

import com.example.demo.dto.BookTransferObject;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(this.bookService.findAll());
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody BookTransferObject bookTransferObject) {
        Book book = this.bookService.save(
                bookTransferObject.getName(),
                Category.valueOf(bookTransferObject.getCategory()),
                bookTransferObject.getAuthorId(),
                bookTransferObject.getAvailableCopies()).get();
        return ResponseEntity.ok(book);
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookTransferObject bookTransferObject) {
        Book book = this.bookService.edit(
                id,
                bookTransferObject.getName(),
                Category.valueOf(bookTransferObject.getCategory()),
                bookTransferObject.getAuthorId(),
                bookTransferObject.getAvailableCopies()).get();
        return ResponseEntity.ok().body(book);
    }
    @PostMapping("/markAsTaken/{id}")
    public void markAsTaken(@PathVariable Long id) {
        this.bookService.markAsTakenBook(id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if(!this.bookService.findById(id).isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}