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

    public List<Book> listAllByHirer(String email) {
        return bookRepository.findByHirerOrderByHirer(email);
    }

    public List<Book> listAll() {
        return this.bookRepository.findAllByOrderByTitle();
    }

    public Book findByTitle(String title) {
        return  this.bookRepository.findByTitle(title);
    }

    public void updateBook(Book updatedBook){
        this.bookRepository.save(updatedBook);
    }


}
