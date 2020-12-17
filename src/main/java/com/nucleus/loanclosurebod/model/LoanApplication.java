package com.nucleus.loanclosurebod.model;

import com.nucleus.customerservice.loandisbursal.model.Customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table( name= "loan_application_new")
public class LoanApplication implements Serializable {

    @Id
    @Column(name = "loan_application_number",length = 10,nullable = false)
    private int loanApplicationNumber;

    //FK
//    @OneToOne
//    @JoinColumn(name = "customer_code", referencedColumnName = "customer_code",nullable = false)
//    private Customer customerCode;

    //FK (Products class required)
//    @ManyToOne
//    @JoinColumn(name = "product_code",referencedColumnName = "product_code",nullable = false)
//    private Product productCode;

    @Column(name = "loan_amount_requested",nullable = false)
    private int loanAmountRequested;

    @Column(name = "tenure",length = 3,nullable = false)
    private int tenure;

    @Column(name = "rate",length = 2,nullable = false)
    private int rate;

    @Column(name = "agreement_date",nullable = false)
    private LocalDate agreementDate;

    @Column(name = "installment_due_date",nullable = false)
    private LocalDate installmentDueDate;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "created_by", length = 30)
    private String createdBy;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "modified_by", length = 30)
    private String modifiedBy;

    @Column(name = "authorized_date")
    private LocalDate authorizedDate;

    @Column(name = "authorized_by", length = 30)
    private String authorizedBy;

    @Column(name = "loan_status", length = 30)
    private String loanStatus;
    // Getter and setter

    public int getLoanApplicationNumber() {
        return loanApplicationNumber;
    }

    public void setLoanApplicationNumber(int loanApplicationNumber) {
        this.loanApplicationNumber = loanApplicationNumber;
    }

//    public Customer getCustomerCode() {
//        return customerCode;
//    }
//
//    public void setCustomerCode(Customer customerCode) {
//        this.customerCode = customerCode;
//    }

//    public String getProductCode() {
//        return productCode;
//    }
//
//    public void setProductCode(String productCode) {
//        this.productCode = productCode;
//    }

    public int getLoanAmountRequested() {
        return loanAmountRequested;
    }

    public void setLoanAmountRequested(int loanAmountRequested) {
        this.loanAmountRequested = loanAmountRequested;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public LocalDate getAgreementDate() {
        return agreementDate;
    }

    public void setAgreementDate(LocalDate agreementDate) {
        this.agreementDate = agreementDate;
    }

    public LocalDate getInstallmentDueDate() {
        return installmentDueDate;
    }

    public void setInstallmentDueDate(LocalDate installmentDueDate) {
        this.installmentDueDate = installmentDueDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDate getAuthorizedDate() {
        return authorizedDate;
    }

    public void setAuthorizedDate(LocalDate authorizedDate) {
        this.authorizedDate = authorizedDate;
    }

    public String getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    @Override
    public String toString() {
        return "LoanApplications{" +
                "loanApplicationNumber=" + loanApplicationNumber +
//                ", customerCode=" + customerCode +
                ", productCode='" + "" + '\'' +
                ", loanAmountRequested=" + loanAmountRequested +
                ", tenure=" + tenure +
                ", rate=" + rate +
                ", agreementDate=" + agreementDate +
                ", installmentDueDate=" + installmentDueDate +
                ", createDate=" + createDate +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", authorizedDate=" + authorizedDate +
                ", authorizedBy='" + authorizedBy + '\'' +
                ", loanStatus='" + loanStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.nucleus.loanclosurebod.model.LoanApplication that = (com.nucleus.loanclosurebod.model.LoanApplication) o;
        return loanApplicationNumber == that.loanApplicationNumber
                && loanAmountRequested == that.loanAmountRequested &&
                tenure == that.tenure && rate == that.rate &&
    //            customerCode.equals(that.customerCode) &&
                agreementDate.equals(that.agreementDate) &&
                installmentDueDate.equals(that.installmentDueDate) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(modifiedDate, that.modifiedDate) &&
                Objects.equals(modifiedBy, that.modifiedBy) &&
                Objects.equals(authorizedDate, that.authorizedDate) &&
                Objects.equals(authorizedBy, that.authorizedBy) &&
                Objects.equals(loanStatus, that.loanStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanApplicationNumber,
                loanAmountRequested, tenure, rate, agreementDate,
                installmentDueDate, createDate, createdBy, modifiedDate,
                modifiedBy, authorizedDate, authorizedBy);
    }
}
