package com.project.estorefront.controller;

import com.project.estorefront.model.*;
import com.project.estorefront.repository.CouponsPersistence;
import com.project.estorefront.repository.IInventoryItemPersistence;
import com.project.estorefront.repository.InventoryItemPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/seller/profile")
    public String sellerProfile() {
        return "seller-profile";
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
        return new ModelAndView("seller-orders", "orders", sellerOrder.getSellerOrders(userID));

    }

    @GetMapping("/seller/orders/current/{orderID}")
    public ModelAndView sellerCurrentOrderView(@PathVariable String orderID) {
        ISellerOrderManagement sellerOrder = new OrderDetails();
        return new ModelAndView("view-selected-order", "order", sellerOrder.getOrderAndItemDetails(orderID));

    }

    @GetMapping("/seller/orders/previous/{orderID}")
    public ModelAndView sellerPreviousOrderView(@PathVariable String orderID) {
        ISellerOrderManagement sellerOrder = new OrderDetails();
        return new ModelAndView("view-selected-order", "order", sellerOrder.getOrderAndItemDetails(orderID));
    }

    @GetMapping("/seller/items/edit/{itemID}")
    public String editSellerItem(@PathVariable String itemID, Model model)
            throws SQLException {
        // TODO: To be implemented
        return "seller-items-update";
    }

    @GetMapping("/seller/items/delete/{itemID}")
    public String deleteSellerItem(@PathVariable String itemID) throws SQLException {
        IInventoryItemPersistence inventoryItemPersistence = new InventoryItemPersistence();
        IInventoryItem item = new InventoryItem();
        item.setItemID(itemID);
        inventoryItemPersistence.delete(item);
        return "redirect:/seller/items";
    }

    @GetMapping("/seller/coupons")
    public String view(Model model) {
        CouponsPersistence persistenceObj = new CouponsPersistence();
        model.addAttribute("coupons", persistenceObj.getCoupons());
        return "view-coupons";
    }

    @GetMapping("/seller/add-coupon")
    public String add() {
        return "add-coupon";
    }

    @PostMapping("/create-coupon")
    public String create(@RequestParam("name") String couponName, @RequestParam("amount") String amount, @RequestParam("percent") String percent) {
        CouponsPersistence persistenceObj = new CouponsPersistence();

        int id = persistenceObj.getCoupons().size() + 1;

        Coupon coupon = new Coupon(id, couponName, Double.parseDouble(amount), Double.parseDouble(percent));
        persistenceObj.saveCoupon(coupon);

        return "redirect:/coupons";
    }

    @RequestMapping(value = "/seller/coupons/view/{id}", method = RequestMethod.GET)
    public String view_details(@PathVariable("id") int id, Model model) {

        CouponsPersistence persistenceObj = new CouponsPersistence();
        model.addAttribute("coupon", persistenceObj.getCouponById(id));

        return "coupon-detail";
    }
//
//    @GetMapping("seller/profile")
//    public String seller



}
