package com.practice.test.booklibrary.book;

import com.practice.test.booklibrary.book.repository.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDAO bookRepository;

    public List<Book> listAll(){
        return bookRepository.listAll();
    }


}
