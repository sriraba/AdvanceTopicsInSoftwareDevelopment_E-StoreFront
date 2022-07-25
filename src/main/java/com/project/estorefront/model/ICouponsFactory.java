package com.project.estorefront.model;

import com.project.estorefront.model.validators.ICouponValidator;
import com.project.estorefront.repository.ICouponsPersistence;

public interface ICouponsFactory {

    ICouponsPersistence makeCouponsPersistence();
    ICoupon makeCoupon(int ID, String Name, double maxAmount, double Percent);
    ICouponValidator makeCouponsValidator();
}
