package com.project.estorefront.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.estorefront.model.Buyer;
import com.project.estorefront.model.BuyerFactory;
import com.project.estorefront.model.CartFactory;
import com.project.estorefront.model.DatabaseFactory;
import com.project.estorefront.model.IBuyerOrderManagement;
import com.project.estorefront.model.ICart;
import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.InventoryFactory;
import com.project.estorefront.model.InventoryItem;
import com.project.estorefront.model.ItemCategory;
import com.project.estorefront.model.OrderAndItemsFactory;
import com.project.estorefront.model.OrderDetails;
import com.project.estorefront.model.Seller;
import com.project.estorefront.model.SellerFactory;
import com.project.estorefront.model.User;
import com.project.estorefront.model.validators.ICartValidator;
import com.project.estorefront.repository.IBuyerOrderPersistence;
import com.project.estorefront.repository.IBuyerPersistence;
import com.project.estorefront.repository.IDatabase;
import com.project.estorefront.repository.IInventoryItemPersistence;
import com.project.estorefront.repository.IOrderPersistence;
import com.project.estorefront.repository.IPlaceOrderPersistence;
import com.project.estorefront.repository.ISellerPersistence;
import com.project.estorefront.repository.PersistenceStatus;

@Controller
public class BuyerController {

    private IDatabase database;
    private ISellerPersistence sellerPersistence;
    private IBuyerPersistence buyerPersistence;
    private IInventoryItemPersistence inventoryItemPersistence;
    private IBuyerOrderPersistence buyerOrderPersistence;
    private IOrderPersistence orderPersistence;

    public BuyerController() {
        database = DatabaseFactory.instance().makeDatabase();
        sellerPersistence = SellerFactory.instance().makeSellerPersistence(database);
        buyerPersistence = BuyerFactory.instance().makeBuyerPersistence(database);
        inventoryItemPersistence = InventoryFactory.instance().makeInventoryItemPersistence(database);
        buyerOrderPersistence = BuyerFactory.instance().makeBuyerOrderPersistence(database);
        orderPersistence = OrderAndItemsFactory.instance().makeOrderPersistence(database);
    }

    private static final String notLoggedInRedirect = "redirect:/login";

    @GetMapping("/buyer")
    public String buyerHome(@RequestParam(required = false, name = "category") String categoryFilter, Model model,
            HttpSession session, RedirectAttributes redirectAttributes) {
        String userID = getUserID(session);
        if (userID == null || userID.isEmpty()) {
            return notLoggedInRedirect;
        }

        ArrayList<User> sellers;
        try {
            if (categoryFilter == null || categoryFilter.isEmpty()) {
                sellers = Seller.getAllSellersByCity(sellerPersistence, "Halifax");
            } else {
                sellers = Seller.getAllSellersByCategory(sellerPersistence, ItemCategory.valueOf(categoryFilter),
                        "Halifax");
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
            @RequestParam("phone") String phone, @RequestParam("address") String address, @PathVariable String userID,
            HttpSession session, RedirectAttributes redirectAttributes) {
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
        IBuyerOrderManagement buyerOrder = BuyerFactory.instance().makeBuyerOrderManagement();

        try {
            Map<String, ArrayList<OrderDetails>> buyerOrders = buyerOrder.getBuyerOrders(userID, buyerOrderPersistence);
            if (buyerOrders == null) {
                redirectAttributes.addFlashAttribute("error", "No orders to show");
                return new ModelAndView("redirect:/buyer");
            }
            return new ModelAndView("buyer-orders", "orders", buyerOrders);
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error fetching orders");
            return new ModelAndView("redirect:/buyer");
        }
    }

    @GetMapping("/buyer/order/details/{orderID}")
    public ModelAndView buyerItems(@PathVariable String orderID, RedirectAttributes redirectAttributes) {
        IBuyerOrderManagement buyerOrder = BuyerFactory.instance().makeBuyerOrderManagement();
        ModelAndView modelAndView = null;
        try {
            OrderDetails orderDetails = buyerOrder.getOrderAndItemDetails(orderID, orderPersistence);
            if (orderDetails == null) {
                redirectAttributes.addFlashAttribute("error", "Something went wrong, please try again.");
                return new ModelAndView("redirect:/buyer", "orders", orderDetails);
            }
            modelAndView = new ModelAndView("view-selected-order", "order", orderDetails);
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
        IBuyerOrderManagement buyerOrder = BuyerFactory.instance().makeBuyerOrderManagement();
        try {
            PersistenceStatus status = buyerOrder.submitReview(userID, orderID, description, buyerOrderPersistence);
            model.addAttribute("page", "buyer");
            if (status == PersistenceStatus.SUCCESS) {
                return "submit-success";
            } else {
                return "redirect:/buyer/orders/view";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error submitting review");
            return "redirect:/buyer-orders/view";
        }

    }

    private ICart getCart(HttpSession session) {
        ICart cart = null;
        if (session.getAttribute("cart") == null) {
            cart = CartFactory.instance().makeCart();
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
    public String addToCart(@PathVariable String itemID, @RequestParam("quantity") String qty, HttpSession session,
            RedirectAttributes redirectAttributes) {
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
    public String deleteItemFromCart(@PathVariable String itemID, Model model, HttpSession session,
            RedirectAttributes redirectAttributes) {
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

    @RequestMapping(value = "/buyer/checkout", method = RequestMethod.POST)
    public String checkout(@RequestParam("address") String address, @RequestParam("pincode") String pincode,
            HttpSession session) throws SQLException {
        ICart cart = getCart(session);
        ICartValidator validator = CartFactory.instance().makeCartValidator();
        String error = validator.validateCart(cart);
        String userID = (String) session.getAttribute("userID");
        if (!error.matches("")) {
            return "redirect:/buyer/cart/view/" + error;
        }
        IPlaceOrderPersistence obj = CartFactory.instance().makeCartPersistence();
        if (obj.placeOrder(cart, userID, address, pincode)) {
            session.setAttribute("cart", "");
            return "thank-you";
        }
        return "redirect:/buyer/cart/view/" + "Error: Unable to place order right now, please try again later!";
    }
}
