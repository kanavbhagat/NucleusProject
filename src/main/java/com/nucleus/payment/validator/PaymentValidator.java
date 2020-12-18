package com.nucleus.payment.validator;

import com.nucleus.payment.model.Payment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PaymentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Payment.class);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
