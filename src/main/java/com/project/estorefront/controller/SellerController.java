package com.project.estorefront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellerController {

    @GetMapping("/seller")
    public String seller() {
        return "seller-page";
    }

    @GetMapping("/seller/items")
    public String sellerItems() {
        return "seller-items";
    }

    @GetMapping("/seller/items/add")
    public String sellerItemsAdd() {
        return "seller-items-add";
    }

    @GetMapping("/seller/items/create")
    public String sellerItemCreate() {
        return "seller-items-add";
    }

}
