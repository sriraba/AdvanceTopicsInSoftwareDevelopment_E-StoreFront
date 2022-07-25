package com.project.estorefront.model.validators;

public interface ICouponValidator {

    boolean isValidAmount(String amount);
    boolean isValidPercent(String percent);
}
