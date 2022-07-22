package com.project.estorefront.controller;

import com.project.estorefront.model.*;
import com.project.estorefront.repository.IInventoryItemPersistence;
import com.project.estorefront.repository.ISellerPersistence;
import com.project.estorefront.repository.InventoryItemPersistence;
import com.project.estorefront.repository.SellerPersistence;
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

    private ICart getCart(HttpSession session)
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

        return cart;
    }
    
    @RequestMapping(value= "/buyer/cart/add/{itemID}", method = RequestMethod.POST)
    public String addToCart(@PathVariable String itemID, @RequestParam("quantity") String qty, HttpSession session)
    {
        ICart cart = getCart(session);
        IInventoryItemPersistence inventoryPersistence = new InventoryItemPersistence();
        IInventoryItem inventory = inventoryPersistence.getItemByID(itemID);
        inventory.setItemQuantity(Integer.parseInt(qty));

        cart.addItem(inventory);
        session.setAttribute("cart", cart);

        return "redirect:/buyer";
    }

    @RequestMapping(value= "/buyer/cart/view", method = RequestMethod.GET)
    public String viewCart(Model model, HttpSession session)
    {
        ICart cart = getCart(session);
        double cartTotal = 0;

        for(IInventoryItem item : cart.getCartItems())
        {
            cartTotal += item.getItemPrice()* item.getItemQuantity();
        }

        model.addAttribute("inventory", cart.getCartItems());
        model.addAttribute("cartTotal", cartTotal);

        return "view-cart";
    }

    @RequestMapping(value= "/buyer/cart/delete/{itemID}", method = RequestMethod.GET)
    public String deleteItemFromCart(@PathVariable String itemID, Model model, HttpSession session)
    {
        ICart cart = getCart(session);
        IInventoryItemPersistence inventoryPersistence = new InventoryItemPersistence();
        IInventoryItem inventory = inventoryPersistence.getItemByID(itemID);

        cart.removeItem(inventory);
        session.setAttribute("cart", cart);
        model.addAttribute("inventory", cart.getCartItems());

        return "view-cart";
    }

    @RequestMapping(value= "/buyer/cart/edit/{itemID}", method = RequestMethod.GET)
    public String viewEditItemQtyPage(@PathVariable String itemID, Model model, HttpSession session)
    {
        ICart cart = getCart(session);
        IInventoryItemPersistence inventoryPersistence = new InventoryItemPersistence();
        IInventoryItem inventory = inventoryPersistence.getItemByID(itemID);

        cart.removeItem(inventory);
        session.setAttribute("cart", cart);
        model.addAttribute("inventory", cart.getCartItems());

        return "view-cart";
    }
}
