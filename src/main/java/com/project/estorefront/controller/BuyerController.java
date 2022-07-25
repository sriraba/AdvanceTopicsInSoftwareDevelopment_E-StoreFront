package com.project.estorefront.controller;

import com.project.estorefront.model.*;
import com.project.estorefront.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class BuyerController {

    private IDatabase database;
    private ISellerPersistence sellerPersistence;
    private IBuyerPersistence buyerPersistence;
    private IInventoryItemPersistence inventoryItemPersistence;
    private IBuyerOrderPersistence buyerOrderPersistence;

    public BuyerController() {
        database = DatabaseFactory.instance().makeDatabase();
        sellerPersistence = SellerFactory.instance().makeSellerPersistence(database);
        buyerPersistence = BuyerFactory.instance().makeBuyerPersistence(database);
        inventoryItemPersistence = InventoryFactory.instance().makeInventoryItemPersistence(database);
        buyerOrderPersistence = BuyerFactory.instance().makeBuyerOrderPersistence(database);
    }


    private static final String notLoggedInRedirect = "redirect:/login";

    @GetMapping("/buyer")
    public String buyerHome(@RequestParam(required = false, name = "category") String categoryFilter, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        String userID = getUserID(session);
        if (userID == null || userID.isEmpty()) {
            return notLoggedInRedirect;
        }

        ArrayList<User> sellers;
        try {
            if (categoryFilter == null || categoryFilter.isEmpty()) {
                sellers = Seller.getAllSellersByCity(sellerPersistence, "Halifax");
            } else {
                sellers = Seller.getAllSellersByCategory(sellerPersistence, ItemCategory.valueOf(categoryFilter), "Halifax");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error fetching nearby sellers");
            return "redirect:/error";
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
    public String buyerAccount(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        String userID = getUserID(session);
        if (userID == null || userID.isEmpty()) {
            return notLoggedInRedirect;
        }

        User buyer = BuyerFactory.instance().makeBuyer();
        try {
            buyer = ((Buyer) buyer).getBuyerByID(buyerPersistence, userID);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error getting buyer account");
            return "redirect:/error";
        }
        model.addAttribute("buyer", buyer);
        return "buyer-account";
    }

    @GetMapping("/buyer/account/edit/{userID}")
    public String editSellerAccount(@PathVariable String userID, Model model, RedirectAttributes redirectAttributes) {
        User buyer = BuyerFactory.instance().makeBuyer();
        try {
            buyer = ((Buyer) buyer).getBuyerByID(buyerPersistence, userID);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error getting buyer account");
            return "redirect:/error";
        }
        model.addAttribute("buyer", buyer);
        return "buyer-account-update";
    }

    @PostMapping("/buyer/account/update/{userID}")
    public String updateBuyerAccount(@RequestParam("firstName") String firstName,
                                     @RequestParam("lastName") String lastName,
                                     @RequestParam("phone") String phone, @RequestParam("address") String address, @PathVariable String userID, HttpSession session, RedirectAttributes redirectAttributes) {
        User buyer = BuyerFactory.instance().makeBuyer();
        buyer.setFirstName(firstName);
        buyer.setLastName(lastName);
        buyer.setPhone(phone);
        buyer.setAddress(address);
        buyer.setUserID(userID);
        try {
            ((Buyer) buyer).updateBuyerAccount(buyerPersistence);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error updating buyer account");
            return "redirect:/error";
        }
        return "redirect:/buyer/account";
    }

    @GetMapping("/buyer/account/deactivate")
    public String deactivateBuyerAccount(HttpSession session, RedirectAttributes redirectAttributes) {
        String userID = getUserID(session);
        if (userID == null || userID.isEmpty()) {
            return notLoggedInRedirect;
        }

        User buyer = BuyerFactory.instance().makeBuyer(userID);
        try {
            ((Buyer) buyer).deactivateBuyerAccount(buyerPersistence);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error deactivating buyer account");
            return "redirect:/error";
        }
        return "redirect:/login";
    }


    @GetMapping("/buyer/view-seller/{sellerID}")
    public String sellerDetails(Model model, @PathVariable String sellerID, RedirectAttributes redirectAttributes) {
        User seller = null;
        try {
            seller = sellerPersistence.getSellerByID(sellerID);
            ArrayList<IInventoryItem> inventory = inventoryItemPersistence.getAll(String.valueOf(sellerID));
            model.addAttribute("seller", seller);
            model.addAttribute("inventory", inventory);

            return "seller-details";
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error fetching coupon");
            return "redirect:/seller/coupons";
        }

    }

    @GetMapping("/buyer/orders/view")
    public ModelAndView buyerOrdersView(HttpSession session, RedirectAttributes redirectAttributes) {
        String userID = (String) session.getAttribute("userID");
        if (userID == null || userID.isEmpty()) {
            return new ModelAndView("redirect:/login");
        }

        IBuyerOrderManagement buyerOrder = new OrderDetails();
        try {
            return new ModelAndView("buyer-orders", "orders", buyerOrder.getBuyerOrders(userID, buyerOrderPersistence));
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error fetching orders");
            return new ModelAndView("redirect:/buyer");
        }
    }

    @GetMapping("/buyer/order/details/{orderID}")
    public ModelAndView buyerItems(@PathVariable String orderID, RedirectAttributes redirectAttributes) {
        IBuyerOrderManagement buyerOrder = new OrderDetails();
        IOrderPersistence orderPersistence = OrderAndItemsFactory.instance().makeOrderPersistence();
        ModelAndView modelAndView = null;
        try {
            modelAndView = new ModelAndView("view-selected-order", "order",
                    buyerOrder.getOrderAndItemDetails(orderID, orderPersistence));
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error fetching order");
            return new ModelAndView("redirect:/error");
        }
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
                               @RequestParam("review") String description, Model model, RedirectAttributes redirectAttributes) {
        IBuyerOrderManagement buyerOrder = new OrderDetails();
        try {
            buyerOrder.submitReview(buyerOrderPersistence, userID, orderID, description);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error submitting review");
            return "redirect:/buyer-orders/view";
        }
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
    public String addToCart(@PathVariable String itemID, @RequestParam("quantity") String qty, HttpSession session, RedirectAttributes redirectAttributes) {
        ICart cart = getCart(session);

        IInventoryItem inventory = null;
        try {
            inventory = inventoryItemPersistence.getItemByID(itemID);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error");
            return "redirect:/buyer";
        }
        ((InventoryItem) inventory).setItemQuantity(Integer.parseInt(qty));

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
    public String deleteItemFromCart(@PathVariable String itemID, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        ICart cart = getCart(session);
        IInventoryItem inventory = null;
        try {
            inventory = inventoryItemPersistence.getItemByID(itemID);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error");
            return "redirect:/buyer";
        }

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
        ((InventoryItem) item).setItemQuantity(Integer.parseInt(quantity));
        cart.updateItem(item);

        return "redirect:/buyer/cart/view";
    }

    private String getUserID(HttpSession session) {
        return (String) session.getAttribute("userID");
    }
}
