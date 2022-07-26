package com.project.estorefront.repository;

import com.project.estorefront.model.Coupon;
import com.project.estorefront.model.ICoupon;

import java.util.ArrayList;
import java.util.List;

public class CouponsPersistenceMock implements ICouponsPersistence{

    private ArrayList<ICoupon> coupons = new ArrayList<>();

    public CouponsPersistenceMock()
    {
        coupons.add(new Coupon(1, "Discount", 50, 10));
        coupons.add(new Coupon(2, "Offer10", 10, 10));
        coupons.add(new Coupon(3, "50% Off", 50, 30));
        coupons.add(new Coupon(4, "New", 40, 25));
        coupons.add(new Coupon(5, "Buy1Get1", 50, 50));
    }

    @Override
    public void saveCoupon(ICoupon coupon){
        if(getCouponById(coupon.getCouponID()) == null)
        {
            coupons.add(coupon);
        }
    }

    @Override
    public void updateCoupon(ICoupon coupon){

        for(int cnt=0; cnt < coupons.size(); cnt++)
        {
            if(coupons.get(cnt).getCouponID() == coupon.getCouponID())
            {
                coupons.set(cnt, coupon);
                break;
            }
        }
    }

    @Override
    public List<ICoupon> getCoupons(){
        return coupons;
    }

    @Override
    public ICoupon getCouponById(int id){
        ICoupon obj = null;

        for(ICoupon coupon : coupons)
        {
            if(coupon.getCouponID() == id)
            {
                obj = coupon;
                break;
            }
        }

        return obj;
    }

    @Override
    public void deleteCoupon(int id){
        for(int cnt=0; cnt < coupons.size(); cnt++)
        {
            if(coupons.get(cnt).getCouponID() == id)
            {
                coupons.remove(cnt);
                break;
            }
        }
    }
}
