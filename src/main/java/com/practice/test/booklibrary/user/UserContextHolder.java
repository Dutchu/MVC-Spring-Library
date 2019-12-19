package com.practice.test.booklibrary.user;

import com.practice.test.booklibrary.user.login.LoginDTO;
import org.springframework.stereotype.Service;

@Service //powoduje ze to jest singletonem
public class UserContextHolder { //info o zalogowanym

    private static String login;

    public static void addUserLogin(String email) {
        login = email;
    }

    public static void logout() {
        login = null;
    }

    public static String getUserLoggedIn() {
        return login;
    }
}
