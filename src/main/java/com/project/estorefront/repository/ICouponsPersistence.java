package com.project.estorefront.repository;
import com.project.estorefront.model.Coupon;

import java.util.List;

public interface ICouponsPersistence {

    void saveCoupon(Coupon coupon);
    void updateCoupon(Coupon coupon);
    List<Coupon> getCoupons();
    Coupon getCouponById(int id);

}
