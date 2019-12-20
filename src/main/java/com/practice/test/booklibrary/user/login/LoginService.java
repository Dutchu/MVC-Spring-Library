package com.practice.test.booklibrary.user.login;

import com.practice.test.booklibrary.user.repository.UserDAO;
import org.springframework.stereotype.Component;

@Component
public class LoginService {
    private UserDAO userDAO;

    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(LoginDTO dto){

        return userDAO.existsByEmail(dto.getLogin());
    }
}
