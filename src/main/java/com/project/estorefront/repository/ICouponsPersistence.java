package com.project.estorefront.repository;

import com.project.estorefront.model.ICoupon;

import java.util.List;

public interface ICouponsPersistence {

    void saveCoupon(ICoupon coupon);
    void updateCoupon(ICoupon coupon);
    List<ICoupon> getCoupons();
    ICoupon getCouponById(int id);
    void deleteCoupon(int id);
}
