package com.project.estorefront.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.estorefront.model.IBuyerOrderManagement;
import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.ItemCategory;
import com.project.estorefront.model.OrderDetails;
import com.project.estorefront.model.Seller;
import com.project.estorefront.model.SellerFactory;
import com.project.estorefront.model.User;
import com.project.estorefront.repository.IInventoryItemPersistence;
import com.project.estorefront.repository.ISellerPersistence;
import com.project.estorefront.repository.InventoryItemPersistence;

@Controller
public class BuyerController {

    @GetMapping("/buyer")
    public String buyerHome(@RequestParam(required = false, name = "category") String categoryFilter, Model model) {
        ISellerPersistence persistence = SellerFactory.instance().makeSellerPersistence();

        ArrayList<User> sellers;
        if (categoryFilter == null || categoryFilter.isEmpty()) {
            sellers = Seller.getAllSellersByCity(persistence, "Halifax");
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

    @GetMapping("/buyer/orders/view/{userID}")
    public ModelAndView buyerOrdersView(@PathVariable String userID) {
        IBuyerOrderManagement buyerOrder = new OrderDetails();
        return new ModelAndView("buyer-orders", "orders", buyerOrder.getBuyerOrders(userID));
    }

    @GetMapping("/buyer/order/details/{orderID}")
    public ModelAndView buyerItems(@PathVariable String orderID) {
        System.out.println("inside /buyer/order/details/{orderID}");
        IBuyerOrderManagement buyerOrder = new OrderDetails();
        ModelAndView modelAndView = new ModelAndView("view-selected-order", "order",
                buyerOrder.getOrderAndItemDetails(orderID));
        modelAndView.addObject("page", "buyer");
        return modelAndView;
    }

}
