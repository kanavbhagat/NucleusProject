package com.nucleus.charge.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

public class NewCharge {

    @NotEmpty(message = "Charge code cannot be empty.")
    private String chargeCode;

    @NotEmpty(message = "Charge Code Name cannot be empty.")
    @Pattern(regexp="^[a-zA-Z]+$", message="Only Alphabets are allowed")
    private String chargeCodeName;

    private String chargeDescription;

    @NotEmpty(message = "Please select a Transaction Event")
    private String transactionEvent;

    @NotEmpty(message = "Please select charge payment type")
    private String chargePaymentType;

    @NotEmpty(message = "Please select a charge type")
    private String chargeType;

    @Digits(integer = 10, fraction = 2, message = "Charge Amount should be of 0000000000.00")
    private double chargeAmount;

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    public String getChargeCodeName() {
        return chargeCodeName;
    }

    public void setChargeCodeName(String chargecodeName) {
        this.chargeCodeName = chargecodeName;
    }

    public String getChargeDescription() {
        return chargeDescription;
    }

    public void setChargeDescription(String chargeDescription) {
        this.chargeDescription = chargeDescription;
    }

    public String getTransactionEvent() {
        return transactionEvent;
    }

    public void setTransactionEvent(String transactionEvent) {
        this.transactionEvent = transactionEvent;
    }

    public String getChargePaymentType() {
        return chargePaymentType;
    }

    public void setChargePaymentType(String chargePaymentType) {
        this.chargePaymentType = chargePaymentType;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }
}
