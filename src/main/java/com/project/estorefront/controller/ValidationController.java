package com.project.estorefront.controller;

import com.project.estorefront.validators.EmailValidator;
import com.project.estorefront.validators.NameValidator;
import com.project.estorefront.validators.PasswordValidator;
import com.project.estorefront.validators.PhoneNumberValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class ValidationController {

    @PostMapping("/validate-login")
    public ModelAndView validateLogin(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("role") String role) {
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();


        if (emailValidator.validate(email) && passwordValidator.validate(password)) {

            if (role.equals("buyer")) {
                return new ModelAndView("redirect:/buyer");
            } else {
                return new ModelAndView("redirect:/seller");
            }

        } else {
            return new ModelAndView("redirect:/login", "error", "Invalid email or password");
        }
    }
    @PostMapping("/validate-register")
    //validate registration
    public ModelAndView validateRegister(@RequestParam("firstName") String firstname, @RequestParam("lastName") String lastName,
                                         @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam ("confirmPassword") String confirmPassword,
                                         @RequestParam("contact") String contact, @RequestParam("city") String city, @RequestParam("role") String role) {
        NameValidator nameValidator = new NameValidator();
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

        ArrayList<String> errors = new ArrayList<>();


        //first name and last name
        if (nameValidator.validate(firstname) == false) {
            errors.add("Invalid First name");
        }

        // city
        if(nameValidator.validate(city)){
            errors.add("Invalid City");
        }
        //phone number
        if (phoneNumberValidator.validate(contact)) {
            errors.add("Invalid Phone Number");

        }
        if(emailValidator.validate(email)){
            errors.add("Invalid email");
        }
        if(passwordValidator.validate(password)) {
            errors.add("Invalid password");
        }
        if(passwordValidator.comparePassword(password, confirmPassword)){
            errors.add("Password not matched");
        }

        if (errors.size() == 0) {
            if (role.equals("buyer")) {
                return new ModelAndView("redirect:/buyer");
            } else if (role.equals("seller")) {
                return new ModelAndView("redirect:/seller");
            } else {
                errors.add("Role");
            }
        }
        String err = String.join(", ", errors);

        System.out.println(err);

        return new ModelAndView("redirect:/login", "error", err);
    }

}
