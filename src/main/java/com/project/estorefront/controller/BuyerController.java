package com.project.estorefront.controller;

import com.project.estorefront.model.*;
import com.project.estorefront.repository.IInventoryItemPersistence;
import com.project.estorefront.repository.ISellerPersistence;
import com.project.estorefront.repository.InventoryItemPersistence;
import com.project.estorefront.repository.SellerPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class BuyerController {

    @GetMapping("/buyer")
    public String buyerHome(@RequestParam(required = false, name = "category") String categoryFilter, Model model) {
        ISellerPersistence persistence = SellerFactory.instance().makeSellerPersistence();


        ArrayList<User> sellers;
        if (categoryFilter == null || categoryFilter.isEmpty()) {
            sellers = Seller.getAllSellers(persistence, "Halifax");
        } else {
            sellers = Seller.getAllSellersByCategory(persistence, ItemCategory.valueOf(categoryFilter), "Halifax");
        }

        model.addAttribute("sellers", sellers);

        ArrayList<String> categories = new ArrayList<>();
        for (ItemCategory category : ItemCategory.values()) {
            categories.add(category.toString());
        }
        model.addAttribute("categories", categories);

        return "buyers";
    }

    @GetMapping("/buyer/view-seller/{sellerID}")
    public String sellerDetails(Model model, @PathVariable int sellerID) {
        ISellerPersistence persistence = SellerFactory.instance().makeSellerPersistence();
        User seller = persistence.getSellerByID(String.valueOf(sellerID));

        IInventoryItemPersistence inventoryPersistence = new InventoryItemPersistence();
        ArrayList<IInventoryItem> inventory = inventoryPersistence.getAll(String.valueOf(sellerID));

        model.addAttribute("seller", seller);
        model.addAttribute("inventory", inventory);

        return "seller-details";
    }

}
