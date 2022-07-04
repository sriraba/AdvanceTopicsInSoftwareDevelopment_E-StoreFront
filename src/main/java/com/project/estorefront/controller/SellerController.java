package com.project.estorefront.controller;

import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.InventoryItem;
import com.project.estorefront.model.ItemCategory;
import com.project.estorefront.repository.InventoryItemPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

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

    @PostMapping("/seller/items/create")
    public String sellerItemCreate(@RequestParam("itemName") String itemName, @RequestParam("description") String itemDescription, @RequestParam("category") String itemCategory, @RequestParam("quantity") int itemQuantity, @RequestParam("price") double itemPrice, HttpSession session) throws SQLException {
        String userID = (String) session.getAttribute("userID");

        IInventoryItem item = new InventoryItem(userID, ItemCategory.valueOf(itemCategory), itemName, itemDescription, itemPrice, itemQuantity);
        item.save(new InventoryItemPersistence());

        return "seller-items-add";
    }

}
