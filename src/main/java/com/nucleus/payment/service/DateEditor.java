package com.nucleus.payment.service;


import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String value) throws IllegalArgumentException {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(value,df);
        setValue(localDate);

    }
}
