package com.project.estorefront.model;

import com.project.estorefront.model.validators.CouponValidator;
import com.project.estorefront.model.validators.ICouponValidator;
import com.project.estorefront.repository.CouponsPersistence;
import com.project.estorefront.repository.ICouponsPersistence;

public class CouponsFactory implements ICouponsFactory{

    private static CouponsFactory instance;

    private CouponsFactory() {
    }

    public static ICouponsFactory instance() {
        if (instance == null) {
            instance = new CouponsFactory();
        }
        return instance;
    }

    @Override
    public ICouponsPersistence makeCouponsPersistence() {
        return new CouponsPersistence();
    }

    @Override
    public ICoupon makeCoupon(int ID, String Name, double maxAmount, double Percent) {
        return new Coupon(ID, Name, maxAmount, Percent);
    }

    @Override
    public ICouponValidator makeCouponsValidator() {
        return new CouponValidator();
    }
}
