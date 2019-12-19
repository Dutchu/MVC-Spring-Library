package com.practice.test.booklibrary.book;

import com.practice.test.booklibrary.book.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookRestController {

    private final BookRepository repository;

    public BookRestController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<Book> getAll() {
        List<Book> books = this.repository.findAll();
        return books;
    }
}
