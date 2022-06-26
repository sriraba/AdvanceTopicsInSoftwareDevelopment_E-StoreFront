package com.project.estorefront.controller;

import com.project.estorefront.validators.EmailValidator;
import com.project.estorefront.validators.PasswordValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

}
