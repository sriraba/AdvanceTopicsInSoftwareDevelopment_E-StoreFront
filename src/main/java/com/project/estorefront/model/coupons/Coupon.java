package com.project.estorefront.model.coupons;

public class Coupon implements ICoupon{

    private final int couponID;
    private final String name;
    private final double maxAmount;
    private final double percent;

    public Coupon(int ID, String Name, double maxAmount, double Percent)
    {
        this.couponID = ID;
        this.name = Name;
        this.maxAmount = maxAmount;
        this.percent = Percent;
    }

    public int getCouponID()
    {
        return couponID;
    }

    public String getName()
    {
        return name;
    }

    public double getMaxAmount()
    {
        return maxAmount;
    }

    public double getPercent()
    {
        return percent;
    }
}
