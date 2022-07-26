package com.project.estorefront.model;

import com.project.estorefront.model.validators.ICouponValidator;
import com.project.estorefront.repository.ICouponsPersistence;
import com.project.estorefront.repository.IDatabase;

public interface ICouponsFactory {

    ICouponsPersistence makeCouponsPersistence(IDatabase database);
    ICoupon makeCoupon(int ID, String Name, double maxAmount, double Percent);
    ICouponValidator makeCouponsValidator();
}
