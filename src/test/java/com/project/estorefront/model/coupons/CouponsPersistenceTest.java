package com.project.estorefront.model.coupons;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

public class CouponsPersistenceTest {

    private ICouponsPersistence mockDB = new CouponsPersistenceMock();

    @Test
    public void testSaveCoupon() throws SQLException {
        ICoupon coupon = new Coupon(6, "Test", 10, 10);
        mockDB.saveCoupon(coupon);

        assertEquals(coupon, mockDB.getCouponById(coupon.getCouponID()));
    }

    @Test
    public void testUpdateCoupon() throws SQLException {
        ICoupon coupon = new Coupon(1, "Discount", 10, 10);
        mockDB.updateCoupon(coupon);

        ICoupon couponFetchedFromDB = mockDB.getCouponById(coupon.getCouponID());
        assertEquals(coupon.getMaxAmount(), couponFetchedFromDB.getMaxAmount());
    }

    @Test
    public void testGetCoupons() throws SQLException {
        assertTrue(mockDB.getCoupons().size() > 0);
    }

    @Test
    public void testGetCouponById() throws SQLException {
        assertEquals(50, mockDB.getCouponById(1).getMaxAmount());
    }

    @Test
    public void testDeleteCoupon() throws SQLException {
        ICoupon coupon = new Coupon(1, "Discount", 50, 10);
        mockDB.deleteCoupon(coupon.getCouponID());

        assertEquals(null, mockDB.getCouponById(coupon.getCouponID()));
    }

}
