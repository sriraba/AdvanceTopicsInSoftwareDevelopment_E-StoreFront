package com.project.estorefront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectionController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

}
