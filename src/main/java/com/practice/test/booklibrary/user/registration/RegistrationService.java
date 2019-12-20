package com.practice.test.booklibrary.user.registration;


import com.practice.test.booklibrary.user.User;
import com.practice.test.booklibrary.user.repository.UserDAO;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {
    UserDAO userDAO;

    public RegistrationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void register(RegistrationDTO dto) {
        if (userDAO.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email istnieje: " + dto.getEmail());
        }

        User user = buildUserFromDTO(dto);
        userDAO.saveUser(user);
    }

    private User buildUserFromDTO(RegistrationDTO dto) {
        User user = new User();
        user.setBirthDate(dto.getBirthDate());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }
}
