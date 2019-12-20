package com.practice.test.booklibrary.user;

import com.practice.test.booklibrary.book.Book;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "Users")
public class User {

    @Id
    private String id;

    private String email;
    private String firstName;
    private String lastName;
    private String birthDate;
    private List<Book> books;

    public User() {
        this.books = new ArrayList<>();
    }

    public User(String email, String firstName, String lastName, String birthDate, List<Book> books) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.books = books;
    }

}
