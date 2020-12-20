package com.nucleus.payment.model;

import com.nucleus.customer.model.Customer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "loan_application_number", length = 10)
    private int loanApplicationNumber;

    @Column(name = "payment_code", length = 10)
    private int paymentCode;

    @Column(name = "payment_amount", length = 8)
    private int paymentAmount;

//    @DateTimeFormat(pattern="dd-MM-yyyy")
    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "payout_bank_account", length = 12)
    private String payoutBankAccount;

//    @ManyToMany
//    @ManyToOne
//    @JoinColumn(name = "customer_code", referencedColumnName = "customer_code")
//    private Customer customerCode;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "payment_channel", length = 30)
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

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPayoutBankAccount() {
        return payoutBankAccount;
    }

    public void setPayoutBankAccount(String payoutBankAccount) {
        this.payoutBankAccount = payoutBankAccount;
    }

//    public Customer getCustomerCode() {
//        return customerCode;
//    }
//
//    public void setCustomerCode(Customer customerCode) {
//        this.customerCode = customerCode;
//    }

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
