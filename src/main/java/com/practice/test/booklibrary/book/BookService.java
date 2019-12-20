package com.practice.test.booklibrary.book;

import com.practice.test.booklibrary.book.repository.BookDAO;
import com.practice.test.booklibrary.user.User;
import com.practice.test.booklibrary.user.UserContextHolder;
import com.practice.test.booklibrary.user.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private UserDAO userDAO;

    public List<Book> listAll(){
        return bookDAO.listAll();
    }

    public List<Book> listAllAvailable(){
        return bookDAO.listAll().stream()
                .filter(Book::getAvailability)
                .collect(Collectors.toList());
    }


//    public void ifRentABook(boolean ifRent) {
//        if(ifRent){
//            rentBook(userContextHolder.getUserLoggedIn(), "dd");
//    }

    public List<Book> listAllForEmail() {
        return bookDAO.listAllByHirer(UserContextHolder.getUserLoggedIn());
    }

    public boolean returnABook(String mail, String title) {
        User userToUpdate;
        Book bookToUpdate;

        userToUpdate = userDAO.findUserByEmail(mail);
        bookToUpdate = bookDAO.findByTitle(title);

        if(bookToUpdate.getAvailability()) return false;

        bookToUpdate.setHirer(null);
        bookToUpdate.setAvailability(true);

        List<Book> booksNotReturned = userToUpdate.getBooks().stream()
                .filter(e -> !e.equals(bookToUpdate))
                .collect(Collectors.toList());

        userToUpdate.setBooks(booksNotReturned);
        userDAO.saveUser(userToUpdate);
        return true;
    }

    public boolean rentABook(String mail, String title) {
        User userToUpdate;
        Book bookToUpdate;

        //take user from database
        userToUpdate = userDAO.findUserByEmail(mail);
        if (userToUpdate == null) return false;

        //take book from database
        bookToUpdate = bookDAO.findByTitle(title);
        if (bookToUpdate == null) return false;

        //check if book unavailable to rent
        if(!bookToUpdate.getAvailability()) return false;

        //update book record with new availability and hirer
        bookToUpdate.setHirer(userToUpdate.getEmail());
        bookToUpdate.setAvailability(false);
        bookDAO.updateBook(bookToUpdate);

        //update user record with new books
        userToUpdate.getBooks().add(bookToUpdate);
        userDAO.updateUser(userToUpdate);
        return true;
    }

}
