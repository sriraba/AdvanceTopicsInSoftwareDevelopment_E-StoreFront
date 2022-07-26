package com.project.estorefront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RedirectionController {

    @GetMapping("/")
    public String redirectToLogin(HttpSession session) {

        String role = (String) session.getAttribute("role");
        String userID = (String) session.getAttribute("userID");

        if (role == null || userID == null) {
            return "redirect:/login";
        } else {
            if (role.equals("seller")) {
                return "redirect:/seller";
            } else if (role.equals("buyer")) {
                return "redirect:/buyer";
            } else {
                session.invalidate();
                return "redirect:/login";
            }
        }
    }

    @GetMapping("/logout")
    public String redirectToLoginOnLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
