package com.practice.test.booklibrary.book;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "Books")//MongoDB
public class Book {
    
    @Id
    private String id;

    private String title;
    private String genre;
    private Integer releaseDate;
    private Boolean availability;
    private String hirer;

    public Book(String title, String genre, Integer releaseDate, Boolean availability, String hirer) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.availability = availability;
        this.hirer = hirer;
    }
}
