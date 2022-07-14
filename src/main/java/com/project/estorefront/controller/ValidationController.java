package com.project.estorefront.controller;

import com.project.estorefront.model.Buyer;
import com.project.estorefront.model.Seller;
import com.project.estorefront.model.User;
import com.project.estorefront.repository.Authentication;
import com.project.estorefront.repository.IAuthentication;
import com.project.estorefront.model.validators.EmailValidator;
import com.project.estorefront.model.validators.NameValidator;
import com.project.estorefront.model.validators.PasswordValidator;
import com.project.estorefront.model.validators.PhoneNumberValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ValidationController {

    @PostMapping("/validate-login")
    public ModelAndView validateLogin(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("role") String role, HttpSession session) {
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();


        if (emailValidator.validate(email) && passwordValidator.validate(password)) {

            IAuthentication authentication = new Authentication();
            Integer userID = authentication.login(email, password);

            session.setAttribute("userID", userID);
            session.setAttribute("role", role);

            if (userID == null) {
                return new ModelAndView("login-page", "error", "Wrong email or password");
            } else {
                if (role.contains("buyer")) {
                    return new ModelAndView("redirect:/buyer");
                } else if (role.contains("seller")) {
                    return new ModelAndView("redirect:/seller");
                } else {
                    return new ModelAndView("redirect:/login", "error", "Please select a role");
                }
            }
        } else {
            return new ModelAndView("redirect:/login", "error", "Invalid email or password");
        }
    }
    @PostMapping("/validate-register")
    public ModelAndView validateRegister(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                         @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam ("confirmPassword") String confirmPassword,
                                         @RequestParam("contact") String contact, @RequestParam("city") String city, @RequestParam String address, @RequestParam("role") String role, HttpSession session) {
        NameValidator nameValidator = new NameValidator();
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

        ArrayList<String> errors = new ArrayList<>();


        if (nameValidator.validate(firstName) == false) {
            errors.add("Invalid First name");
        }

        if(nameValidator.validate(city) == false){
            errors.add("Invalid City");
        }
        //phone number
        if (phoneNumberValidator.validate(contact) == false) {
            errors.add("Invalid Phone Number");

        }
        if(emailValidator.validate(email) == false){
            errors.add("Invalid email");
        }
        if(passwordValidator.validate(password) == false) {
            errors.add("Invalid password");
        }
        if(passwordValidator.comparePassword(password, confirmPassword) == false){
            errors.add("Password not matched");
        }

        if (errors.size() == 0) {

            IAuthentication authentication = new Authentication();
            User user = null;

            if (role.contains("buyer")) {
                user = new Buyer(firstName, lastName, email, address, contact, password, city, false);
            } else if (role.contains("seller")) {
                user = new Seller(firstName, lastName, email, address, contact, password, city, true);
            }

            System.out.println(role);

            Integer userID = authentication.register(user);
            System.out.println(userID);
            session.setAttribute("userID", userID);
            session.setAttribute("role", role);

            if (role.contains("buyer") && userID != null) {
                return new ModelAndView("redirect:/buyer");
            } else if (role.contains("seller") && userID != null) {
                return new ModelAndView("redirect:/seller");
            } else if (userID == null) {
                return new ModelAndView("redirect:/register", "error", "Something went wrong");
            }  else {
                errors.add("Role");
            }
        }
        String err = String.join(", ", errors);

        return new ModelAndView("redirect:/register", "error", err);
    }
}
