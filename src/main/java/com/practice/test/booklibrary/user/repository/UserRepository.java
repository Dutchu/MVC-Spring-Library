package com.practice.test.booklibrary.user.repository;


import com.practice.test.booklibrary.user.User;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /*
• Add new student – cannot enter student with the same data twice.
• List only available (not rented to any student) books
• List all books sorted alphabetically by title. If a book is currently rented to one of the
students, then the student data (all fields) should be displayed.
• Rent a book – assign a book to the client.
• Return a book – remove a book-client assignment.
• List the books rented by particular user.
     */
    User findByEmail(String email);

    boolean existsByEmail(String email);


}
