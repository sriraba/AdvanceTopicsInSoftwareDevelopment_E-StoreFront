package com.project.estorefront;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstorefrontController {

    @RequestMapping
    public String eStorefrontWelcome()
    {
        return "Welcome to eStorefront";
    }
}
