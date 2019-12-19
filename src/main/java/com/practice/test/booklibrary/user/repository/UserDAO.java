package com.practice.test.booklibrary.user.repository;

import com.practice.test.booklibrary.user.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Getter
@Service
@Component
public class UserDAO {

    @Autowired
    private UserRepository userRepository;

    private List<User> userList = readUsers();

    private List<User> readUsers() {
        return userRepository != null ? userRepository.findAll() : new ArrayList<>();
    }

    public boolean emailExists(String email) {
        return Optional.ofNullable(this.userRepository.findByEmail(email)).isPresent();
    }

    public Optional<User> findUserByEmail(String login) {
        return Optional.ofNullable(this.userRepository.findByEmail(login));
    }

    public void saveUser(User user) {
        this.userRepository.insert(user);
    }
}
