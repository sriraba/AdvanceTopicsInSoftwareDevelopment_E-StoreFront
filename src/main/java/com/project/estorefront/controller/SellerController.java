package com.project.estorefront.controller;

import com.project.estorefront.model.*;
import com.project.estorefront.repository.IInventoryItemPersistence;
import com.project.estorefront.repository.InventoryItemPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class SellerController {

    private String mockUserID;

    public SellerController() {
        mockUserID = "1";
    }

    @GetMapping("/seller")
    public String seller() {
        return "seller-page";
    }

    @GetMapping("/seller/items")
    public String sellerItems(Model model, HttpSession session) {
        IInventoryItemPersistence inventoryItemPersistence = new InventoryItemPersistence();

        String userID = (String) session.getAttribute("userID");

        // TODO: Once seller dashboard is created, update 1 param to userID
        ArrayList<IInventoryItem> all = inventoryItemPersistence.getAll(mockUserID);
        model.addAttribute("items", all);

        return "seller-items";
    }

    @GetMapping("/seller/items/add")
    public String sellerItemsAdd() {
        return "seller-items-add";
    }

    @PostMapping("/seller/items/create")
    public String createSellerItem(@RequestParam("itemName") String itemName,
            @RequestParam("description") String itemDescription, @RequestParam("category") String itemCategory,
            @RequestParam("quantity") int itemQuantity, @RequestParam("price") double itemPrice, HttpSession session)
            throws SQLException {
        String userID = (String) session.getAttribute("userID");

        // TODO: Once seller dashboard is created, update 1 param to userID
        IInventoryItem item = new InventoryItem(mockUserID, ItemCategory.valueOf(itemCategory), itemName,
                itemDescription, itemPrice, itemQuantity);
        item.save(new InventoryItemPersistence());

        return "seller-items-add";
    }

    @GetMapping("/seller/orders/view/{userID}")
    public ModelAndView sellerOrdersView(@PathVariable String userID) {
        ISellerOrderManagement sellerOrder = new OrderDetails();
        return new ModelAndView("seller-orders","orders", sellerOrder.getSellerOrders(userID));

    }

    @GetMapping("/seller/orders/current/{orderID}")
    public ModelAndView sellerCurrentOrderView(@PathVariable String orderID){
        ISellerOrderManagement sellerOrder = new OrderDetails();
        return new ModelAndView("view-selected-order","order", sellerOrder.getOrderAndItemDetails(orderID));

    }
    @GetMapping("/seller/orders/previous/{orderID}")
    public ModelAndView sellerPreviousOrderView(@PathVariable String orderID) {
        ISellerOrderManagement sellerOrder = new OrderDetails();
        return new ModelAndView("view-selected-order","order", sellerOrder.getOrderAndItemDetails(orderID));
    }

    @PostMapping("/seller/items/update")
    public String updateSellerItem(@RequestParam("itemName") String itemName,
            @RequestParam("description") String itemDescription, @RequestParam("category") String itemCategory,
            @RequestParam("quantity") int itemQuantity, @RequestParam("price") double itemPrice, HttpSession session)
            throws SQLException {
        // TODO: To be implemented
        return "seller-items-update";
    }

    @GetMapping("/seller/items/delete")
    public String deleteSellerItem(@RequestParam("itemID") String itemID) throws SQLException {
        IInventoryItemPersistence inventoryItemPersistence = new InventoryItemPersistence();
        IInventoryItem item = new InventoryItem();
        item.setItemID(itemID);
        inventoryItemPersistence.delete(item);
        return "seller-items";
    }

}
