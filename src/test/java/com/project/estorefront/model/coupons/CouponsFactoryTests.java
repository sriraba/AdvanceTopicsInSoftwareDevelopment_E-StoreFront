package com.project.estorefront.model.coupons;

import com.project.estorefront.model.coupons.CouponsFactory;
import com.project.estorefront.model.coupons.ICoupon;
import com.project.estorefront.model.validators.ICouponValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CouponsFactoryTests {

    @Test
    public void testCouponsFactoryInstance() {
        assertNotNull(CouponsFactory.instance());
    }

    @Test
    public void testMakeCoupon()
    {
        ICoupon coupon = CouponsFactory.instance().makeCoupon(1, "Discount", 50, 10);
        assertNotNull(coupon);
    }

    public void testMakeCouponsValidator()
    {
        ICouponValidator validator = CouponsFactory.instance().makeCouponsValidator();
        assertNotNull(validator);
    }


}
