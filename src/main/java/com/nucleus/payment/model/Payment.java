package com.nucleus.payment.model;

import com.nucleus.customer.model.Customer;

import jdk.vm.ci.meta.Local;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @DateTimeFormat(pattern="dd-MM-yyyy")
    @NotNull(message = "Please Enter A Date")
    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @NotBlank(message = "Bank account cannot be empty")
    @Column(name = "payout_bank_account", length = 12)
    private String payoutBankAccount;

//    @ManyToMany
//    @ManyToOne
//    @JoinColumn(name = "customer_code", referencedColumnName = "customer_code")

    @NotBlank(message = "Customer code cannot be empty")
    @Column(name = "customer_code")
    private String customerCode;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "payment_channel", length = 30)
    private String paymentChannel;

    @Column(name = "reviewed_by", length = 20)
    private String reviewedBy;

    @Column(name = "payment_status", length = 10)
    private String paymentStatus;

    @Column(name = "made_by", length = 20)
    private String madeBy;

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
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
