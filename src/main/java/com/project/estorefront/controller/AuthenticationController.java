package com.project.estorefront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "login-page";
    }

    @GetMapping("/register")
    public String register()
    {
        return "register-page";
    }

}
