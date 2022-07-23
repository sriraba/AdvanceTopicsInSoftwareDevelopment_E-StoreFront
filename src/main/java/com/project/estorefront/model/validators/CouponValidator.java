package com.project.estorefront.model.validators;

public class CouponValidator implements ICouponValidator{

    @Override
    public boolean isValidAmount(String amount) {

        try{
            Double amt = Double.parseDouble(amount);

            if(amt <= 0 || amt > 100)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (Exception e)
        {
            return false;
        }

    }

    @Override
    public boolean isValidPercent(String percent) {

        try {
            Double amt = Double.parseDouble(percent);

            if (amt <= 0 || amt > 100)
            {
                return false;
            }
            else
            {
                return true;
            }

        }
        catch (Exception e)
        {
            return false;
        }
    }
}
