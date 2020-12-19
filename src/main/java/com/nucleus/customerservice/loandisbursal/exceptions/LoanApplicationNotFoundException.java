package com.nucleus.customerservice.loandisbursal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LoanApplicationNotFoundException extends RuntimeException{
    public LoanApplicationNotFoundException(String message) {
        super(message);
    }
}
