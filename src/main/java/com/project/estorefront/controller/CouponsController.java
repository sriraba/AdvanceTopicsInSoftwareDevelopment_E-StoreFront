package com.project.estorefront.controller;

import com.project.estorefront.repository.CouponsPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CouponsController {

    @GetMapping("/coupons")
    public String view(Model model) {
        CouponsPersistence persistenceObj = new CouponsPersistence();
        model.addAttribute("coupons", persistenceObj.getCoupons());
        return "view-coupons";
    }
}
