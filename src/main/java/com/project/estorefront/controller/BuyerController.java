package com.project.estorefront.controller;

import com.project.estorefront.model.User;
import com.project.estorefront.repository.ISellerPersistence;
import com.project.estorefront.repository.SellerPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class BuyerController {

    @GetMapping("/buyers")
    public String seller(Model model) {
        ISellerPersistence persistence = new SellerPersistence();
        ArrayList<User> sellers = persistence.getAllSellersByCity("Halifax");
        System.out.println(sellers.get(0).getCity());
        model.addAttribute("sellers", sellers);
        return "buyers";
    }

}
