package com.practice.test.booklibrary.book.repository;

import com.practice.test.booklibrary.book.Book;
import com.practice.test.booklibrary.book.repository.BookRepository;
import lombok.Getter;

import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class BookDAO {

    private final BookRepository bookRepository;

    public BookDAO(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }
}
