package com.project.estorefront.model.validators;

import com.project.estorefront.model.coupons.CouponsFactory;
import com.project.estorefront.model.validators.ICouponValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CouponValidatorTest {

    @Test
    public void testIsValidAmount()
    {
        ICouponValidator validator = CouponsFactory.instance().makeCouponsValidator();

        assertTrue(validator.isValidAmount("10"));
        assertFalse(validator.isValidAmount("fe"));
        assertFalse(validator.isValidAmount("10a"));
        assertFalse(validator.isValidAmount("-50"));
        assertFalse(validator.isValidAmount("1000"));
    }

    @Test
    public void testIsValidPercent()
    {
        ICouponValidator validator = CouponsFactory.instance().makeCouponsValidator();

        assertTrue(validator.isValidPercent("10.59"));
        assertFalse(validator.isValidPercent("fe"));
        assertFalse(validator.isValidPercent("10a"));
        assertFalse(validator.isValidPercent("-50"));
        assertFalse(validator.isValidPercent("1000"));
    }
}
