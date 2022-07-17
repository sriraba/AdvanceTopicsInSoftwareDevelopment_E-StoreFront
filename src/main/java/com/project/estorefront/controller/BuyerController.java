package com.project.estorefront.controller;

import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.User;
import com.project.estorefront.repository.IInventoryItemPersistence;
import com.project.estorefront.repository.ISellerPersistence;
import com.project.estorefront.repository.InventoryItemPersistence;
import com.project.estorefront.repository.SellerPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class BuyerController {

    @GetMapping("/buyer")
    public String buyerHome(Model model) {
        ISellerPersistence persistence = new SellerPersistence();
        ArrayList<User> sellers = persistence.getAllSellersByCity("Halifax");
        System.out.println(sellers.get(0).getCity());
        model.addAttribute("sellers", sellers);
        return "buyers";
    }

    @GetMapping("/buyer/view-seller/{sellerID}")
    public String sellerDetails(Model model, @PathVariable int sellerID) {
        ISellerPersistence persistence = new SellerPersistence();
        User seller = persistence.getSellerByID(String.valueOf(sellerID));

        IInventoryItemPersistence inventoryPersistence = new InventoryItemPersistence();
        ArrayList<IInventoryItem> inventory = inventoryPersistence.getAll(String.valueOf(sellerID));

        model.addAttribute("seller", seller);
        model.addAttribute("inventory", inventory);

        return "seller-details";
    }

}
