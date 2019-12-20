package com.practice.test.booklibrary;

import com.practice.test.booklibrary.book.Book;
import com.practice.test.booklibrary.book.BookService;
import com.practice.test.booklibrary.user.login.LoginDTO;
import com.practice.test.booklibrary.user.login.LoginService;
import com.practice.test.booklibrary.user.registration.RegistrationDTO;
import com.practice.test.booklibrary.user.registration.RegistrationValidationService;
import com.practice.test.booklibrary.user.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.practice.test.booklibrary.user.*;
import com.practice.test.booklibrary.user.registration.RegistrationService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class OnlyOneController {

    @Autowired
    private BookService bookService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private UserContextHolder userContextHolder;


    @RequestMapping("/")
    public String ok() {
        return "index";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {//model zapewniony przez Spring
        RegistrationDTO registrationDTO = new RegistrationDTO(); //pusty obiekt na dane (formularz)
        model.addAttribute("form", registrationDTO);
        return "registerPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerEffect(RegistrationDTO registrationDTO, Model model) {
        RegistrationValidationService registrationValidationService = new RegistrationValidationService();
        Map<String, String> errorsMap = registrationValidationService.validate(registrationDTO);
        model.addAttribute("form", registrationDTO);
        if (errorsMap.isEmpty()) { //dane są poprawne
            return tryToRegisterUser(registrationDTO, model);
        }
        model.addAllAttributes(errorsMap);
        return "registerPage";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("form", loginDTO);
        return "loginPage";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginEffect(LoginDTO dto, Model model) {
        boolean ableToLogin = loginService.login(dto);
        if (ableToLogin) {
            UserContextHolder.addUserLogin(dto.getLogin());
            return "index";
        } else {
            model.addAttribute("form", dto);
            model.addAttribute("errorMessage", "Błąd logowania");
            return "loginPage";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        UserContextHolder.logout();
        return "index";
    }

    @RequestMapping(value = "/books-all", method = RequestMethod.GET)
    public String allBooks(Model model){
        List<Book> listBooks  = bookService.listAll();
        model.addAttribute("listBooks", listBooks);
        return "allBooks";
    }

    @RequestMapping(value = "/your-books", method = RequestMethod.GET)
    public String yourBooks(Model model){
        List<Book> yourBooks  = bookService.listAllForEmail();
        model.addAttribute("yourBooks", yourBooks);
        return "yourBooks";
    }

    @RequestMapping(value = "/rent", method = RequestMethod.GET)
    public String allBooksAvailable(Model model){
        List<Book> allAvailableBooks = bookService.listAllAvailable();
        model.addAttribute("allAvailableBooks", allAvailableBooks);
        return "rentPage";
    }
//    @RequestMapping(value = "/rentEffect", method = RequestMethod.POST)
//    public String rentABook(Model model){
//        String title = "1";
//        boolean ifRentABook = bookService.ifRentABook;
//        model.addAttribute("title", title);
//        model.addAttribute("ifRentABook", ifRentABook);
//
//        return "rentEffect";
//    }

    private String tryToRegisterUser(RegistrationDTO registrationDTO, Model model) {
        try {
            registrationService.register(registrationDTO);
        } catch (Exception e) {
            model.addAttribute("userExistsExceptionMessage", e.getMessage());
            return "registerPage";
        }
        return "helloPage";
    }
}