package com.example.demo.service.ServiceImpl;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(RuntimeException::new);
        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
    authorRepository.deleteById(id);
    }
}
