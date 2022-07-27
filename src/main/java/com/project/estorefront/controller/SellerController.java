package com.project.estorefront.controller;

import com.project.estorefront.model.IPropertiesReader;
import com.project.estorefront.model.analytics.AnalyticsPersistence;
import com.project.estorefront.model.analytics.IAnalyticsPersistence;
import com.project.estorefront.model.coupons.Coupon;
import com.project.estorefront.model.coupons.CouponsFactory;
import com.project.estorefront.model.coupons.ICouponsPersistence;
import com.project.estorefront.model.database.DatabaseFactory;
import com.project.estorefront.model.database.IDatabase;
import com.project.estorefront.model.inventory.*;
import com.project.estorefront.model.ordermanagement.*;
import com.project.estorefront.model.user.ISellerPersistence;
import com.project.estorefront.model.user.Seller;
import com.project.estorefront.model.user.SellerFactory;
import com.project.estorefront.model.user.User;
import com.project.estorefront.model.validators.CouponValidator;
import com.project.estorefront.model.validators.IInventoryItemValidator;
import com.project.estorefront.model.validators.InventoryItemValidationStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class SellerController {

    private IDatabase database;
    private final ISellerPersistence sellerPersistence;
    private final IInventoryItemPersistence inventoryItemPersistence;
    private final ICouponsPersistence couponsPersistence;
    private final IOrderPersistence orderPersistence;
    private final ISellerOrderPersistence sellerOrderPersistence;
    private final IDeliveryPersonPersistence deliveryPersonPersistence;

    public SellerController() {
        database = DatabaseFactory.instance().makeDatabase();
        sellerPersistence = SellerFactory.instance().makeSellerPersistence(database);
        inventoryItemPersistence = InventoryFactory.instance().makeInventoryItemPersistence(database);
        couponsPersistence = CouponsFactory.instance().makeCouponsPersistence(database);
        orderPersistence = OrderAndItemsFactory.instance().makeOrderPersistence(database);
        sellerOrderPersistence = SellerFactory.instance().makeSellerOrderPersistence(database);
        deliveryPersonPersistence = DeliveryPersonFactory.instance().makeDeliveryPersonPersistence(database);
    }

    private static final String notLoggedInRedirect = "redirect:/login";

    @GetMapping("/seller")
    public String seller() {
        return "seller-page";
    }

    @GetMapping("/seller/account")
    public String sellerAccount(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        String userID = getUserID(session);
        if (userID == null || userID.isEmpty()) {
            return notLoggedInRedirect;
        }

        User seller = SellerFactory.instance().makeSeller();
        try {
            seller = ((Seller) seller).getSellerByID(sellerPersistence, userID);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error getting seller profile.");
            return "redirect:/seller";
        }
        model.addAttribute("seller", seller);
        return "seller-account";
    }

    @GetMapping("/seller/account/edit/{userID}")
    public String editSellerAccount(@PathVariable String userID, Model model, RedirectAttributes redirectAttributes) {
        User seller = new Seller();
        try {
            seller = ((Seller) seller).getSellerByID(sellerPersistence, userID);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error getting seller profile.");
            return "redirect:/seller";
        }
        model.addAttribute("seller", seller);
        return "seller-account-update";
    }

    @PostMapping("/seller/account/update/{userID}")
    public String updateSellerAccount(@RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName, @RequestParam("businessName") String businessName,
                                      @RequestParam("businessDescription") String businessDescription,
                                      @RequestParam("email") String email, @RequestParam("phone") String phone, @PathVariable String userID,
                                      RedirectAttributes redirectAttributes) {

        User seller = new Seller();
        seller.setFirstName(firstName);
        seller.setLastName(lastName);
        ((Seller) seller).setBusinessName(businessName);
        ((Seller) seller).setBusinessDescription(businessDescription);
        seller.setPhone(phone);
        seller.setUserID(userID);
        try {
            ((Seller) seller).updateSellerAccount(sellerPersistence);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error updating seller profile.");
            return "redirect:/seller";
        }
        return "redirect:/seller/account";
    }

    @GetMapping("/seller/account/deactivate")
    public String deactivateSellerAccount(HttpSession session) throws SQLException {
        String userID = getUserID(session);
        if (userID == null || userID.isEmpty()) {
            return notLoggedInRedirect;
        }

        User seller = SellerFactory.instance().makeSeller(userID);

        ((Seller) seller).deactivateSellerAccount(sellerPersistence);
        return "redirect:/login";
    }

    @GetMapping("/seller/items")
    public String sellerItems(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        String userID = getUserID(session);
        if (userID == null || userID.isEmpty()) {
            return notLoggedInRedirect;
        }

        ArrayList<IInventoryItem> all = null;
        try {
            all = inventoryItemPersistence.getAll(userID);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error getting inventory items");
            return "redirect:/seller/items";
        }
        model.addAttribute("items", all);
        return "seller-items";
    }

    @GetMapping("/seller/items/add")
    public String sellerItemsAdd(HttpSession session) {
        String userID = getUserID(session);
        if (userID == null || userID.isEmpty()) {
            return notLoggedInRedirect;
        }

        return "seller-items-add";
    }

    @PostMapping("/seller/items/create")
    public String createSellerItem(@RequestParam("itemName") String itemName,
                                   @RequestParam("description") String itemDescription, @RequestParam("category") String itemCategory,
                                   @RequestParam(value = "quantity", defaultValue = "0") int itemQuantity,
                                   @RequestParam(value = "price", defaultValue = "0") double itemPrice, HttpSession session,
                                   RedirectAttributes redirectAttributes)
            throws SQLException {
        String userID = getUserID(session);
        if (userID == null || userID.isEmpty()) {
            return notLoggedInRedirect;
        }

        IInventoryItem item = new InventoryItem(userID, ItemCategory.valueOf(itemCategory), itemName,
                itemDescription, itemPrice, itemQuantity);

        IInventoryItemValidator validator = InventoryFactory.instance().makeValidator();
        InventoryItemValidationStatus validationStatus = validator.validate(item);

        if (validationStatus.equals(InventoryItemValidationStatus.VALID)) {
            IInventoryItemPersistence.InventoryItemPersistenceOperationStatus status = item
                    .save(inventoryItemPersistence);

            if (status == IInventoryItemPersistence.InventoryItemPersistenceOperationStatus.SUCCESS) {
                redirectAttributes.addFlashAttribute("success", "Item added successfully.");
                return "redirect:/seller/items";
            } else {
                redirectAttributes.addFlashAttribute("error", "Something went wrong. Please try again.");
                return "redirect:/seller/items/add";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", validationStatus.label);
            return "redirect:/seller/items/add";
        }
    }

    @GetMapping("/seller/orders/view")
    public ModelAndView sellerOrdersView(HttpSession session, RedirectAttributes redirectAttributes) {
        String userID = getUserID(session);
        if (userID == null || userID.isEmpty()) {
            return new ModelAndView(notLoggedInRedirect);
        }

        ISellerOrderManagement sellerOrder = SellerFactory.instance().makeSellerOrderManagement();

        try {
            Map<String, ArrayList<OrderDetails>> sellerOrders = sellerOrder.getSellerOrders(userID, sellerOrderPersistence);
            if (sellerOrders == null) {
                redirectAttributes.addFlashAttribute("error", "No orders to show");
                return new ModelAndView("redirect:/seller");
            }
            return new ModelAndView("seller-orders", "orders", sellerOrders);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error getting orders");
            return new ModelAndView("redirect:/seller");
        }
    }

    @GetMapping("/seller/orders/current/{orderID}")
    public ModelAndView sellerCurrentOrderView(@PathVariable String orderID, RedirectAttributes redirectAttributes) {
        ISellerOrderManagement sellerOrder = SellerFactory.instance().makeSellerOrderManagement();
        ModelAndView modelAndView = null;
        try {
            OrderDetails orderDetails = sellerOrder.getOrderAndItemDetails(orderID, orderPersistence);
            if (orderDetails == null) {
                redirectAttributes.addFlashAttribute("error", "Something went wrong, please try again.");
                return new ModelAndView("redirect:/seller");
            }
            modelAndView = new ModelAndView("view-selected-order", "order", orderDetails);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error getting order");
            return new ModelAndView("redirect:/seller/orders/view");
        }
        modelAndView.addObject("page", "current");
        return modelAndView;
    }

    @GetMapping("/seller/orders/previous/{orderID}")
    public ModelAndView sellerPreviousOrderView(@PathVariable String orderID, RedirectAttributes redirectAttributes) {
        ISellerOrderManagement sellerOrder = SellerFactory.instance().makeSellerOrderManagement();
        ModelAndView modelAndView = null;
        try {
            OrderDetails orderDetails = sellerOrder.getOrderAndItemDetails(orderID, orderPersistence);
            if (orderDetails == null) {
                redirectAttributes.addFlashAttribute("error", "Something went wrong, please try again.");
                return new ModelAndView("redirect:/seller", "orders", orderDetails);
            }
            modelAndView = new ModelAndView("view-selected-order", "order", orderDetails);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error getting order");
            return new ModelAndView("redirect:/seller/orders/view");
        }
        modelAndView.addObject("page", "previous");
        return modelAndView;
    }

    @GetMapping("/seller/orders/assign_delivery_person/{orderID}")
    public ModelAndView assignDeliveryPerson(@PathVariable String orderID, RedirectAttributes redirectAttributes,HttpSession session) throws SQLException {
        String sellerID = getUserID(session);
        ModelAndView modelAndView = null;
        IDeliveryPerson deliveryPerson = SellerFactory.instance().makeDeliveryPerson();
        ArrayList<IDeliveryPerson> deliveryPersonDetails = deliveryPerson.getDeliveryPersonDetails(sellerID, deliveryPersonPersistence);

        if (deliveryPersonDetails == null) {
            redirectAttributes.addFlashAttribute("error", "Something went wrong, please try again.");
            return new ModelAndView("redirect:/seller/orders/view");
        }
        modelAndView = new ModelAndView("assign-delivery-person", "delivery_persons", deliveryPersonDetails);
        modelAndView.addObject("orderID", orderID);
        return modelAndView;
    }

    @GetMapping("/seller/orders/assigned/{orderID}")
    public String deliveryPersonAssigned(Model model,@PathVariable String orderID,RedirectAttributes redirectAttributes) {
        ISellerOrderManagement sellerOrder = SellerFactory.instance().makeSellerOrderManagement();
        try{
            model.addAttribute("page", "seller");
            IPropertiesReader.PersistenceStatus status = sellerOrder.updateOrderStatus(orderID, sellerOrderPersistence);
            if (status == IPropertiesReader.PersistenceStatus.SUCCESS) {
                return "submit-success";
            } else {
                return "redirect:/seller/orders/view";
            }
        }catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error Assigning delivery person");
        }
        return "submit-success";
    }

    @GetMapping("/seller/items/edit/{itemID}")
    public String editSellerItem(@PathVariable String itemID, Model model, HttpSession session) {
        String userID = getUserID(session);
        if (userID == null || userID.isEmpty()) {
            return notLoggedInRedirect;
        }

        IInventoryItem item = null;
        try {
            item = inventoryItemPersistence.getItemByID(itemID);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error getting item");
            return "redirect:/seller/items";
        }
        model.addAttribute("item", item);
        return "seller-items-update";
    }

    @PostMapping("/seller/items/update/{itemID}")
    public String updateSellerItem(@RequestParam("itemName") String itemName,
                                   @RequestParam("description") String itemDescription, @RequestParam("category") String itemCategory,
                                   @RequestParam("quantity") int itemQuantity, @RequestParam("price") double itemPrice,
                                   @PathVariable String itemID, HttpSession session, RedirectAttributes redirectAttributes)
            throws SQLException {
        String userID = getUserID(session);
        if (userID == null || userID.isEmpty()) {
            return notLoggedInRedirect;
        }

        IInventoryItem item = InventoryFactory.instance().makeInventoryItem(itemID, userID,
                ItemCategory.valueOf(itemCategory), itemQuantity, itemPrice, itemName, itemDescription);

        IInventoryItemValidator validator = InventoryFactory.instance().makeValidator();
        InventoryItemValidationStatus validationStatus = validator.validate(item);

        if (validationStatus.equals(InventoryItemValidationStatus.VALID)) {
            IInventoryItemPersistence.InventoryItemPersistenceOperationStatus status = item
                    .update(inventoryItemPersistence);

            if (status == IInventoryItemPersistence.InventoryItemPersistenceOperationStatus.SUCCESS) {
                redirectAttributes.addFlashAttribute("success", "Item updated successfully.");
                return "redirect:/seller/items";
            } else {
                redirectAttributes.addFlashAttribute("error", "Something went wrong. Please try again.");
                return "redirect:/seller/items/edit/" + itemID;
            }
        } else {
            redirectAttributes.addFlashAttribute("error", validationStatus.label);
            return "redirect:/seller/items/edit/" + itemID;
        }
    }

    @GetMapping("/seller/items/delete/{itemID}")
    public String deleteSellerItem(@PathVariable String itemID, HttpSession session) throws SQLException {
        String userID = getUserID(session);
        if (userID == null || userID.isEmpty()) {
            return notLoggedInRedirect;
        }

        IInventoryItem item = InventoryFactory.instance().makeInventoryItemWithItemID(itemID);

        item.delete(inventoryItemPersistence);
        return "redirect:/seller/items";
    }

    @GetMapping("/seller/coupons")
    public String view(Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            model.addAttribute("coupons", couponsPersistence.getCoupons());
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error getting coupons");
            return "redirect:/seller/coupons";
        }
        return "view-coupons";
    }

    @GetMapping("/seller/add-coupon")
    public String add(Model model) {
        model.addAttribute("error", "");
        return "add-coupon";
    }

    @GetMapping("/seller/add-coupon/{error}")
    public String addCoupon(Model model, @PathVariable("error") String error) {
        model.addAttribute("error", error);
        return "add-coupon";
    }

    @PostMapping("/seller/create-coupon")
    public String create(@RequestParam("name") String couponName, @RequestParam("amount") String amount,
                         @RequestParam("percent") String percent, RedirectAttributes redirectAttributes) {
        int id = 0;
        try {
            id = couponsPersistence.getCoupons().size() + 1;
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error getting coupons");
            return "redirect:/seller/coupons";
        }

        String error = "";
        CouponValidator validator = new CouponValidator();
        if (!validator.isValidAmount(amount)) {
            error = "Error: Invalid Amount";
            return "redirect:/seller/add-coupon/" + error;
        } else if (!validator.isValidPercent(percent)) {
            error = "Error: Invalid percent";
            return "redirect:/seller/add-coupon/" + error;
        } else {
            Coupon coupon = new Coupon(id, couponName, Double.parseDouble(amount), Double.parseDouble(percent));
            try {
                couponsPersistence.saveCoupon(coupon);
            } catch (SQLException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Error saving coupon");
                return "redirect:/seller/coupons";
            }
        }

        return "redirect:/seller/coupons";
    }

    @RequestMapping(value = "/seller/coupons/view/{id}", method = RequestMethod.GET)
    public String viewCouponDetails(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("coupon", couponsPersistence.getCouponById(id));
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error loading coupon");
            return "redirect:/seller/coupons";
        }

        return "coupon-detail";
    }

    @RequestMapping(value = "/seller/coupons/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        try {
            couponsPersistence.deleteCoupon(id);
            model.addAttribute("coupons", couponsPersistence.getCoupons());
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error deleting coupn");
            return "redirect:/seller/coupons";
        }

        return "view-coupons";
    }

    @RequestMapping(value = "/seller/coupons/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("coupon", couponsPersistence.getCouponById(id));
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error fetching coupon");
            return "redirect:/seller/coupons";
        }

        return "edit-coupon";
    }

    @RequestMapping(value = "/seller/coupons/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @RequestParam("name") String couponName,
                         @RequestParam("amount") String amount, @RequestParam("percent") String percent,
                         RedirectAttributes redirectAttributes) {
        CouponValidator validator = new CouponValidator();
        if (validator.isValidPercent(percent) && validator.isValidAmount(amount)) {
            Coupon coupon = new Coupon(id, couponName, Double.parseDouble(amount), Double.parseDouble(percent));
            try {
                couponsPersistence.updateCoupon(coupon);
            } catch (SQLException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Error fetching coupon");
                return "redirect:/seller/coupons";

            }
            return "redirect:/seller/coupons";
        } else {
            return "redirect:/seller/coupons/edit/" + id;
        }
    }

    private String getUserID(HttpSession session) {
        return (String) session.getAttribute("userID");
    }

    @RequestMapping(value = "/seller/analytics", method = RequestMethod.GET)
    public String viewAnalytics(Model model, HttpSession session) {
        String userID = getUserID(session);
        IAnalyticsPersistence obj = new AnalyticsPersistence();
        model.addAttribute("totalSales", obj.getTotalSales(userID));
        model.addAttribute("totalOrders", obj.getTotalOrders(userID));
        model.addAttribute("totalReturningCustomers", obj.getTotalReturningBuyers(userID));
        return "view-analytics";
    }

}
