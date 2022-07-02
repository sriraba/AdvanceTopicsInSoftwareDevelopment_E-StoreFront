package com.project.estorefront.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class SellerController {

    @GetMapping("/seller")
    public String login() {
        return "seller-page";
    }

}
