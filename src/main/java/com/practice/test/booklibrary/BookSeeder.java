package com.practice.test.booklibrary;

import com.practice.test.booklibrary.book.Book;
import com.practice.test.booklibrary.book.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BookSeeder implements CommandLineRunner {

    private final BookRepository bookRepository;

    public BookSeeder(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
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
        List<Book> books = Arrays.asList(crimeAndPunishment, hearthOfDarkness, lastWish);
        this.bookRepository.insert(books);


    }
}
