package com.project.estorefront.repository;

import com.project.estorefront.model.ICoupon;

import java.sql.SQLException;
import java.util.List;

public interface ICouponsPersistence {

    void saveCoupon(ICoupon coupon) throws SQLException;
    void updateCoupon(ICoupon coupon) throws SQLException;
    List<ICoupon> getCoupons() throws SQLException;
    ICoupon getCouponById(int id) throws SQLException;
    void deleteCoupon(int id) throws SQLException;
}
