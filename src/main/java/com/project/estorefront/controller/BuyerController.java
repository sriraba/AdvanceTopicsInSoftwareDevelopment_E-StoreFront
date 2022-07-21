package com.project.estorefront.controller;

import com.project.estorefront.model.*;
import com.project.estorefront.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class BuyerController {

    @GetMapping("/buyer")
    public String buyerHome(@RequestParam(required = false, name = "category") String categoryFilter, Model model) {
        ISellerPersistence persistence = new SellerPersistence();


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
        ISellerPersistence persistence = new SellerPersistence();
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
        return new ModelAndView("buyer-orders","orders", buyerOrder.getBuyerOrders(userID));
    }

    @GetMapping("/buyer/order/details/{orderID}")
    public ModelAndView buyerItems(@PathVariable String orderID) {
        IBuyerOrderManagement buyerOrder = new OrderDetails();
        ModelAndView modelAndView = new ModelAndView("view-selected-order","order", buyerOrder.getOrderAndItemDetails(orderID));
        modelAndView.addObject("page","buyer");
        return modelAndView;
    }

    @GetMapping("/buyer/order/add-review/{userID}/{orderID}")
    public String addReview(@PathVariable("userID") String userID,@PathVariable("orderID") String orderID,Model model) {
        model.addAttribute("userID",userID);
        model.addAttribute("orderID",orderID);
        return "add-review";
    }
    @GetMapping("/buyer/order/submit-review/{userID}/{orderID}")
    public String submitReview(@PathVariable("userID") String userID,@PathVariable("orderID") String orderID, @RequestParam("review") String description, Model model) {
        IBuyerOrderManagement buyerOrder = new OrderDetails();
        buyerOrder.submitReview(userID,orderID,description);
        model.addAttribute("page","buyer");
        return "submit-success";
    }

    @GetMapping("/buyer/account")
    public String buyerAccount(Model model, HttpSession session) {
        IBuyerPersistence buyerPersistence = new BuyerPersistence();
        String userID = (String) session.getAttribute("userID");
        User buyer = new Buyer();
        buyer = ((Buyer)buyer).getBuyerByID(buyerPersistence, "1");
        model.addAttribute("buyer", buyer);
        return "buyer-account";
    }

    @GetMapping("/buyer/account/edit/{userID}")
    public String editSellerAccount(@PathVariable String userID, Model model) {
        IBuyerPersistence buyerPersistence = new BuyerPersistence();
        User buyer = new Buyer();
        buyer = ((Buyer)buyer).getBuyerByID(buyerPersistence, userID);
        model.addAttribute("buyer", buyer);
        return "buyer-account-update";
    }

    @PostMapping("/Buyer/account/update/{userID}")
    public String updateBuyerAccount(@RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName,
                                      @RequestParam("email") String email,
                                     @RequestParam("phone") String phone, @PathVariable String userID, HttpSession session) {
        User buyer = new Buyer();
        buyer.setFirstName(firstName);
        buyer.setLastName(lastName);
        buyer.setEmail(email);
        buyer.setPhone(phone);
        buyer.setUserID(userID);
        IBuyerPersistence buyerPersistence = new BuyerPersistence();
        ((Buyer) buyer).updateBuyerAccount(buyerPersistence);
        return "redirect:/buyer/account";
    }

    @GetMapping("/buyer/account/deactivate")
    public String deactivateBuyerAccount() throws SQLException {
        User buyer = new Buyer();
        buyer.setUserID("1");
        IBuyerPersistence buyerPersistence = new BuyerPersistence();
        ((Buyer) buyer).deactivateBuyerAccount(buyerPersistence);
        return "redirect:/login";
    }

    //public ModelAndView addToCart()
    @RequestMapping(value= "/buyer/cart/add/{itemID}", method = RequestMethod.POST)
    public String addToCart(@PathVariable String itemID, @RequestParam("quantity") String qty, HttpSession session)
    {
        ICart cart = null;
        if(session.getAttribute("cart") == null)
        {
            cart = Cart.instance();
        }
        else
        {
            cart = (ICart)session.getAttribute("cart");
        }

        IInventoryItemPersistence inventoryPersistence = new InventoryItemPersistence();
        IInventoryItem inventory = inventoryPersistence.getItemByID(itemID);
        inventory.setItemQuantity(Integer.parseInt(qty));

        cart.addItem(inventory);
        session.setAttribute("cart", cart);

        return "redirect:/buyer";
    }

    //public ModelAndView addToCart()
    @RequestMapping(value= "/buyer/cart/view", method = RequestMethod.GET)
    public String viewCart(Model model, HttpSession session)
    {
        ICart cart = null;
        if(session.getAttribute("cart") == null)
        {
            cart = Cart.instance();
        }
        else
        {
            cart = (ICart)session.getAttribute("cart");
        }

        model.addAttribute("inventory", cart.getCartItems());

        return "view-cart";
    }
}
