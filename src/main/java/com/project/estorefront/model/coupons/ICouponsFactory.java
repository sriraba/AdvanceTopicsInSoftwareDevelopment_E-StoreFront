package com.project.estorefront.model.coupons;

import com.project.estorefront.model.validators.ICouponValidator;
import com.project.estorefront.model.database.IDatabase;

public interface ICouponsFactory {

    ICouponsPersistence makeCouponsPersistence(IDatabase database);
    ICoupon makeCoupon(int ID, String Name, double maxAmount, double Percent);
    ICouponValidator makeCouponsValidator();
}
