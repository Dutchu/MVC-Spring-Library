package com.practice.test.booklibrary.user.registration;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegistrationValidationService {
    public Map<String, String> validate(RegistrationDTO dto){
        Map<String, String> errorMap = new HashMap<>();
        String firstName = dto.getFirstName(); //todo remove this messy code
        String lastName = dto.getLastName();
        String email = dto.getEmail();
        String birthDate = dto.getBirthDate();

        if (firstName == null || !firstName.matches("[A-Z][a-z]{2,}")){
            errorMap.put("firstNameValRes", "Powinny być przynajmniej 3 znaki");
        }
        if (lastName == null || !lastName.matches("^[A-Z][a-z]{2,}(-[A-Z][a-z]*)?$")){
            errorMap.put("lastNameValRes", "Przynajmniej 3 znaki oraz pierwsza duża, reszta małe \n" +
                    "\t+ dopuszczenie \"-\" i drugiego członu z dużej litery");
        }
        if (email == null || !email.matches("^[\\w\\.]{2,}@([a-z]{2,}\\.){1,2}[a-z]{2,}")){
            errorMap.put("emailValRes", "inny jest, mowie ci");
        }
        if (birthDate == null || !birthDate.matches("^(19|20)[0-9]{2}-((0[1-9])|(1[0-2]))-((0[1-9])|([12][0-9])|(3[01]))$")) {
            errorMap.put("birthDateValRes", "no chyba nie");
        }

        return errorMap;
    }
}
