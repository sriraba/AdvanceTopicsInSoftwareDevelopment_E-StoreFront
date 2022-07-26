package com.project.estorefront.model;

import com.project.estorefront.model.validators.ICouponValidator;
import com.project.estorefront.repository.ICouponsPersistence;
import com.project.estorefront.repository.IDatabase;
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
