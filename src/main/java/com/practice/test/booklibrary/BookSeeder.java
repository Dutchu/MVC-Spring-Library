package com.practice.test.booklibrary;

import com.practice.test.booklibrary.user.repository.UserRepository;
import org.apache.commons.logging.Log;
import com.practice.test.booklibrary.book.Book;
import com.practice.test.booklibrary.book.BookService;
import com.practice.test.booklibrary.book.repository.BookRepository;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BookSeeder implements CommandLineRunner {

    private static final Log log = LogFactory.getLog(CommandLineRunner.class);

    private final BookRepository bookRepository;
    private final BookService bookService;
    private final UserRepository userRepository;

    public BookSeeder(BookRepository bookRepository, BookService bookService, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book a = new Book(
                "A",
                "x",
                12,
                true,
                null
        );
        Book b = new Book(
                "B",
                "y",
                13,
                true,
                null
        );
        Book c = new Book(
                "C",
                "z",
                14,
                true,
                null
        );

        Book crimeAndPunishment = new Book(
                "Crime and Punishment",
                "Philosophical Novel",
                1866,
                true,
                null
        );
        Book hearthOfDarkness = new Book(
                "Hearth of Darkness",
                "Novella",
                1899,
                true,
                null
        );


        Book lastWish = new Book(
                "Last Wish",
                "Fantasy",
                1993,
                false,
                "Transition Technologies"
        );

        this.bookRepository.deleteAll();
//        this.userRepository.deleteAll();
        List<Book> books = Arrays.asList(crimeAndPunishment, hearthOfDarkness, lastWish, a,b,c);
        this.bookRepository.insert(books);

        boolean success = bookService.rentABook("blaszczyk17@gmail.com", "Crime and Punishment");
        if (success) {
            log.info("Book rented");
        } else {
            log.info("Book unavailable");
        }
//        success = bookService.returnABook("blaszczyk17@gmail.com", "Crime and Punishment");
//        if (success) {
//            log.info("Book returned");
//        } else {
//            log.info("Book is not by the user  rented");
//        }
    }
}
