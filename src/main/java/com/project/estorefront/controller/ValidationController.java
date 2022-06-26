package com.project.eStorefront.controller;

import com.project.eStorefront.validators.EmailValidator;
import com.project.eStorefront.validators.PasswordValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ValidationController {

    @PostMapping("/validate-login")
    public ModelAndView validate(@RequestParam("email") String email, @RequestParam("password") String password) {
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();

        if (emailValidator.validate(email) && passwordValidator.validate(password)) {
            return new ModelAndView("redirect:/");
        } else {
            return new ModelAndView("redirect:/login", "error", "Invalid email or password");
        }
    }

}
