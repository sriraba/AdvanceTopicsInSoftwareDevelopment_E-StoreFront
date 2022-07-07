package com.project.estorefront.controller;

import com.project.estorefront.model.Coupon;
import com.project.estorefront.repository.CouponsPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CouponsController {

    @GetMapping("/coupons")
    public String view(Model model) {
        CouponsPersistence persistenceObj = new CouponsPersistence();
        model.addAttribute("coupons", persistenceObj.getCoupons());
        return "view-coupons";
    }

    @GetMapping("/add-coupon")
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

    @RequestMapping(value= "/coupons/view/{id}", method = RequestMethod.GET)
    public String view_details(@PathVariable("id") int id, Model model ) {

        CouponsPersistence persistenceObj = new CouponsPersistence();
        model.addAttribute("coupon", persistenceObj.getCouponById(id));

        return  "coupon-detail";
    }
}
