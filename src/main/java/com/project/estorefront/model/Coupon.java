package com.project.estorefront.model;

public class Coupon {

    private int coupon_id;
    private String name;
    private double max_amt;
    private double percent;

    public Coupon(int ID, String Name, double Max_Amt, double Percent)
    {
        coupon_id = ID;
        name = Name;
        max_amt = Max_Amt;
        percent = Percent;
    }

    public int getCouponID()
    {
        return coupon_id;
    }

    public String getName()
    {
        return name;
    }
}
