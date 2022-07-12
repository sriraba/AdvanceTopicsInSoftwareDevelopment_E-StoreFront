package com.project.estorefront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuyerController {

    @GetMapping("/buyers")
    public String seller() {
        return "buyers";
    }

}
