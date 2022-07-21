package com.project.estorefront.controller;

import com.project.estorefront.model.*;
import com.project.estorefront.repository.CouponsPersistence;
import com.project.estorefront.repository.IInventoryItemPersistence;
import com.project.estorefront.repository.InventoryItemPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                                   @RequestParam("quantity") int itemQuantity, @RequestParam("price") double itemPrice, HttpSession session, RedirectAttributes redirAttrs)
            throws SQLException {
        String userID = (String) session.getAttribute("userID");

        // TODO: Once seller dashboard is created, update 1 param to userID
        IInventoryItem item = new InventoryItem(mockUserID, ItemCategory.valueOf(itemCategory), itemName,
                itemDescription, itemPrice, itemQuantity);
        IInventoryItemPersistence.InventoryItemPersistenceOperationStatus status = item.save(new InventoryItemPersistence());

        if (status == IInventoryItemPersistence.InventoryItemPersistenceOperationStatus.SUCCESS) {
            return "redirect:/seller/items";
        } else {
            redirAttrs.addFlashAttribute("error", "Something went wrong. Please try again.");
            return "redirect:/seller/items/add";
        }
    }

    @GetMapping("/seller/orders/view/{userID}")
    public ModelAndView sellerOrdersView(@PathVariable String userID) {
        ISellerOrderManagement sellerOrder = new OrderDetails();
        return new ModelAndView("seller-orders", "orders", sellerOrder.getSellerOrders(userID));

    }

    @GetMapping("/seller/orders/current/{orderID}")
    public ModelAndView sellerCurrentOrderView(@PathVariable String orderID) {
        ISellerOrderManagement sellerOrder = new OrderDetails();
        ModelAndView modelAndView = new ModelAndView("view-selected-order", "order", sellerOrder.getOrderAndItemDetails(orderID));
        modelAndView.addObject("page", "current");
        return modelAndView;
    }

    @GetMapping("/seller/orders/previous/{orderID}")
    public ModelAndView sellerPreviousOrderView(@PathVariable String orderID) {
        ISellerOrderManagement sellerOrder = new OrderDetails();
        ModelAndView modelAndView = new ModelAndView("view-selected-order", "order", sellerOrder.getOrderAndItemDetails(orderID));
        modelAndView.addObject("page", "previous");
        return modelAndView;
    }

    @GetMapping("/seller/orders/assign_delivery_person/{sellerID}")
    public ModelAndView assignDeliveryPerson(@PathVariable String sellerID) {
        IDeliveryPerson deliveryPersons = new DeliveryPerson();
        return new ModelAndView("assign-delivery-person", "delivery_persons", deliveryPersons.getDeliveryPersonDetails(sellerID));
    }

    @GetMapping("/seller/orders/assigned")
    public String deliveryPersonAssigned(Model model) {
        model.addAttribute("page","seller");
        return "submit-success";
    }

    @GetMapping("/seller/items/edit/{itemID}")
    public String editSellerItem(@PathVariable String itemID, Model model) {
        //TODO change comparison from string to enum in .html
        IInventoryItemPersistence inventoryItemPersistence = new InventoryItemPersistence();
        IInventoryItem item = inventoryItemPersistence.getItemByID(itemID);
        model.addAttribute("item", item);
        return "seller-items-update";
    }

    @PostMapping("/seller/items/update/{itemID}")
    public String updateSellerItem(@RequestParam("itemName") String itemName,
                                   @RequestParam("description") String itemDescription, @RequestParam("category") String itemCategory,
                                   @RequestParam("quantity") int itemQuantity, @RequestParam("price") double itemPrice, @PathVariable String itemID, HttpSession session, RedirectAttributes redirAttrs) {
        IInventoryItemPersistence inventoryItemPersistence = new InventoryItemPersistence();
        IInventoryItem item = new InventoryItem(mockUserID, ItemCategory.valueOf(itemCategory), itemName,
                itemDescription, itemPrice, itemQuantity);
        item.setItemID(itemID);
        IInventoryItemPersistence.InventoryItemPersistenceOperationStatus status = item.update(inventoryItemPersistence);

        if (status == IInventoryItemPersistence.InventoryItemPersistenceOperationStatus.SUCCESS) {
            return "redirect:/seller/items";
        } else {
            redirAttrs.addFlashAttribute("error", "Something went wrong. Please try again.");
            return "redirect:/seller/items/update/" + itemID;
        }
    }

    @GetMapping("/seller/items/delete/{itemID}")
    public String deleteSellerItem(@PathVariable String itemID) throws SQLException {
        IInventoryItemPersistence inventoryItemPersistence = new InventoryItemPersistence();
        IInventoryItem item = new InventoryItem();
        item.setItemID(itemID);
        item.delete(inventoryItemPersistence);
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

    @PostMapping("/seller/create-coupon")
    public String create(@RequestParam("name") String couponName, @RequestParam("amount") String amount, @RequestParam("percent") String percent) {
        CouponsPersistence persistenceObj = new CouponsPersistence();

        int id = persistenceObj.getCoupons().size() + 1;

        Coupon coupon = new Coupon(id, couponName, Double.parseDouble(amount), Double.parseDouble(percent));
        persistenceObj.saveCoupon(coupon);

        return "redirect:/seller/coupons";
    }

    @RequestMapping(value = "/seller/coupons/view/{id}", method = RequestMethod.GET)
    public String view_details(@PathVariable("id") int id, Model model) {

        CouponsPersistence persistenceObj = new CouponsPersistence();
        model.addAttribute("coupon", persistenceObj.getCouponById(id));

        return "coupon-detail";
    }

    @RequestMapping(value = "/seller/coupons/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id, Model model) {

        CouponsPersistence persistenceObj = new CouponsPersistence();
        persistenceObj.deleteCoupon(id);
        model.addAttribute("coupons", persistenceObj.getCoupons());

        return "view-coupons";
    }

    @RequestMapping(value = "/seller/coupons/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        CouponsPersistence persistenceObj = new CouponsPersistence();
        model.addAttribute("coupon", persistenceObj.getCouponById(id));

        return "edit-coupon";
    }

    @RequestMapping(value = "/seller/coupons/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @RequestParam("name") String couponName, @RequestParam("amount") String amount, @RequestParam("percent") String percent) {
        CouponsPersistence persistenceObj = new CouponsPersistence();

        Coupon coupon = new Coupon(id, couponName, Double.parseDouble(amount), Double.parseDouble(percent));
        persistenceObj.updateCoupon(coupon);

        return "redirect:/seller/coupons";
    }

}
