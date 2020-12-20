package com.nucleus.chargepolicy.validation;

import com.nucleus.chargepolicy.model.ChargePolicy;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MyValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ChargePolicy.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChargePolicy chargePolicy = new ChargePolicy();
        if(chargePolicy.getChargePolicyCode()==null) errors.rejectValue("chargePolicyCode", "", "Not Null ");
    }
}
