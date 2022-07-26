package com.project.estorefront.model.coupons;

import com.project.estorefront.model.coupons.Coupon;
import com.project.estorefront.model.coupons.ICoupon;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CouponTests {

    private ICoupon coupon = null;

    public CouponTests()
    {

    }

    @Test
    public void testCreateNewCoupon()
    {
        coupon = new Coupon(1, "Discount", 50, 10);
        assertNotNull(coupon);
    }

    @Test
    public void testGetCouponID()
    {
        coupon = new Coupon(1, "Discount", 50, 10);
        assertEquals(1, coupon.getCouponID());
    }

    @Test
    public void testGetName()
    {
        coupon = new Coupon(1, "Discount", 50, 10);
        assertEquals("Discount", coupon.getName());
    }

    @Test
    public void testGetMaxAmount()
    {
        coupon = new Coupon(1, "Discount", 50, 10);
        assertEquals(50, coupon.getMaxAmount());
    }

    @Test
    public void testGetPercent()
    {
        coupon = new Coupon(1, "Discount", 50, 10);
        assertEquals(10, coupon.getPercent());
    }
}
