package com.example.demo.web;

import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/author")
public class AuthorContoller {

    private final AuthorService authorService;

    public AuthorContoller(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> listAllAuthors(){
    return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        return ResponseEntity.of(authorService.findById(id));
    }



}
