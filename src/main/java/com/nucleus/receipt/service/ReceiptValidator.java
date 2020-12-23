package com.nucleus.receipt.service;

import com.nucleus.receipt.model.Receipt;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReceiptValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Receipt.class);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}