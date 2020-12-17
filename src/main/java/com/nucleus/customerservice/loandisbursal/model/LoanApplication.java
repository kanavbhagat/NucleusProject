package com.nucleus.customerservice.loandisbursal.model;


import com.nucleus.product.model.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table( name= "loan_application")
public class LoanApplication implements Serializable {
    @Id
    @Column(name = "loan_application_number",length = 10,nullable = false)
    private int loanApplicationNumber;

    //FK
    @ManyToOne
    @JoinColumn(name = "customer_code", referencedColumnName = "customer_code",nullable = false)
    private Customer customerCode;

    //FK
    @ManyToOne
    @JoinColumn(name = "product_code",referencedColumnName = "product_code",nullable = false)
    private Product productCode;

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

    // Getter and setter

    public int getLoanApplicationNumber() {
        return loanApplicationNumber;
    }

    public void setLoanApplicationNumber(int loanApplicationNumber) {
        this.loanApplicationNumber = loanApplicationNumber;
    }

    public Customer getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Customer customerCode) {
        this.customerCode = customerCode;
    }

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

    public Product getProductCode() {
        return productCode;
    }

    public void setProductCode(Product productCode) {
        this.productCode = productCode;
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "loanApplicationNumber=" + loanApplicationNumber +
                ", customerCode=" + customerCode +
                ", productCode=" + productCode +
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
                '}';
    }
}
