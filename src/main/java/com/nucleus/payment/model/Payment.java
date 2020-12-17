package com.nucleus.payment.model;

import com.nucleus.customer.model.Customer;

import java.time.LocalDate;

public class Payment {
    private int loanApplicationNumber;
    private int paymentCode;
    private int paymentAmount;
    private LocalDate date;
    private String payoutBankAccount;
    private Customer customerCode;
    private String remarks;
    private String paymentChannel;

    public int getLoanApplicationNumber() {
        return loanApplicationNumber;
    }

    public void setLoanApplicationNumber(int loanApplicationNumber) {
        this.loanApplicationNumber = loanApplicationNumber;
    }

    public int getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(int paymentCode) {
        this.paymentCode = paymentCode;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPayoutBankAccount() {
        return payoutBankAccount;
    }

    public void setPayoutBankAccount(String payoutBankAccount) {
        this.payoutBankAccount = payoutBankAccount;
    }

    public Customer getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Customer customerCode) {
        this.customerCode = customerCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }
}
