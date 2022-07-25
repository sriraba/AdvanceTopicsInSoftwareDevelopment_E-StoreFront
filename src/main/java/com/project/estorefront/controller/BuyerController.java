package com.project.estorefront.controller;

import com.project.estorefront.model.*;
import com.project.estorefront.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

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

    @PostMapping("/buyer/account/update/{userID}")
    public String updateBuyerAccount(@RequestParam("firstName") String firstName,
                                     @RequestParam("lastName") String lastName,
                                     @RequestParam("phone") String phone, @RequestParam("address") String address, @PathVariable String userID, HttpSession session) {
        User buyer = new Buyer();
        buyer.setFirstName(firstName);
        buyer.setLastName(lastName);
        buyer.setPhone(phone);
        buyer.setAddress(address);
        buyer.setUserID(userID);
        IBuyerPersistence buyerPersistence = new BuyerPersistence();
        ((Buyer) buyer).updateBuyerAccount(buyerPersistence);
        return "redirect:/buyer/account";
    }

    @GetMapping("/buyer/account/deactivate")
    public String deactivateBuyerAccount() {
        User buyer = new Buyer();
        buyer.setUserID("1");
        IBuyerPersistence buyerPersistence = new BuyerPersistence();
        ((Buyer) buyer).deactivateBuyerAccount(buyerPersistence);
        return "redirect:/login";
    }


    @GetMapping("/buyer/view-seller/{sellerID}")
    public String sellerDetails(Model model, @PathVariable int sellerID) {
        ISellerPersistence persistence = SellerFactory.instance().makeSellerPersistence();
        User seller = persistence.getSellerByID(String.valueOf(sellerID));

        IInventoryItemPersistence inventoryPersistence = InventoryFactory.instance().makeInventoryItemPersistence();
        ArrayList<IInventoryItem> inventory = inventoryPersistence.getAll(String.valueOf(sellerID));

        model.addAttribute("seller", seller);
        model.addAttribute("inventory", inventory);

        return "seller-details";
    }

    @GetMapping("/buyer/orders/view")
    public ModelAndView buyerOrdersView(HttpSession session) {
        String userID = (String) session.getAttribute("userID");
        if(userID == null || userID.isEmpty()){
            return new ModelAndView("redirect:/login");
        }
        IBuyerOrderManagement buyerOrder = new OrderDetails();
        IBuyerOrderPersistence orderPersistence = BuyerFactory.instance().makeBuyerOrderPersistence();
        return new ModelAndView("buyer-orders","orders", buyerOrder.getBuyerOrders(userID, orderPersistence));
    }

    @GetMapping("/buyer/order/details/{orderID}")
    public ModelAndView buyerItems(@PathVariable String orderID) {
        IBuyerOrderManagement buyerOrder = new OrderDetails();
        IOrderPersistence orderPersistence = OrderAndItemsFactory.instance().makeOrderPersistence();
        ModelAndView modelAndView = new ModelAndView("view-selected-order", "order",
                buyerOrder.getOrderAndItemDetails(orderID,orderPersistence));
        modelAndView.addObject("page", "buyer");
        return modelAndView;
    }

    @GetMapping("/buyer/order/add-review/{userID}/{orderID}")
    public String addReview(@PathVariable("userID") String userID, @PathVariable("orderID") String orderID,
            Model model) {
        model.addAttribute("userID", userID);
        model.addAttribute("orderID", orderID);
        return "add-review";
    }

    @GetMapping("/buyer/order/submit-review/{userID}/{orderID}")
    public String submitReview(@PathVariable("userID") String userID, @PathVariable("orderID") String orderID,
            @RequestParam("review") String description, Model model) {
        IBuyerOrderManagement buyerOrder = new OrderDetails();
        buyerOrder.submitReview(userID, orderID, description);
        model.addAttribute("page", "buyer");
        return "submit-success";
    }

    private ICart getCart(HttpSession session) {
        ICart cart = null;
        if (session.getAttribute("cart") == null) {
            cart = Cart.instance();
        } else {
            cart = (ICart) session.getAttribute("cart");
        }
        return cart;
    }

    private double getCartTotal(ICart cart) {
        double cartTotal = 0;
        for (IInventoryItem item : cart.getCartItems()) {
            cartTotal += item.getItemPrice() * item.getItemQuantity();
        }
        return cartTotal;
    }

    @RequestMapping(value = "/buyer/cart/add/{itemID}", method = RequestMethod.POST)
    public String addToCart(@PathVariable String itemID, @RequestParam("quantity") String qty, HttpSession session) {
        ICart cart = getCart(session);
        IInventoryItemPersistence inventoryPersistence = new InventoryItemPersistence();
        IInventoryItem inventory = inventoryPersistence.getItemByID(itemID);
        inventory.setItemQuantity(Integer.parseInt(qty));

        cart.addItem(inventory);
        session.setAttribute("cart", cart);

        return "redirect:/buyer";
    }

    @RequestMapping(value = "/buyer/cart/view", method = RequestMethod.GET)
    public String viewCart(Model model, HttpSession session) {
        ICart cart = getCart(session);
        model.addAttribute("inventory", cart.getCartItems());
        model.addAttribute("cartTotal", getCartTotal(cart));

        return "view-cart";
    }

    @RequestMapping(value = "/buyer/cart/delete/{itemID}", method = RequestMethod.GET)
    public String deleteItemFromCart(@PathVariable String itemID, Model model, HttpSession session) {
        ICart cart = getCart(session);
        IInventoryItemPersistence inventoryPersistence = new InventoryItemPersistence();
        IInventoryItem inventory = inventoryPersistence.getItemByID(itemID);

        cart.removeItem(inventory);
        session.setAttribute("cart", cart);
        model.addAttribute("inventory", cart.getCartItems());

        return "view-cart";
    }

    @RequestMapping(value = "/buyer/cart/edit/{itemID}", method = RequestMethod.GET)
    public String viewEditItemQtyPage(@PathVariable String itemID, Model model, HttpSession session) {
        ICart cart = getCart(session);

        IInventoryItem item = cart.getItemByID(itemID);
        if (item == null) {
            model.addAttribute("inventory", cart.getCartItems());
            model.addAttribute("cartTotal", getCartTotal(cart));
            return "view-cart";
        }

        model.addAttribute("item", item);
        return "cart-item-update";
    }

    @PostMapping("/buyer/cart/update/{itemID}")
    public String updateCartItemQty(@PathVariable String itemID, @RequestParam("quantity") String quantity,
            HttpSession session) {
        ICart cart = getCart(session);
        IInventoryItem item = cart.getItemByID(itemID);
        item.setItemQuantity(Integer.parseInt(quantity));
        cart.updateItem(item);

        return "redirect:/buyer/cart/view";
    }
}
