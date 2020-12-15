package com.nucleus.loanaplications.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "loan_application")
public class LoanApplications {

    @Id
    @Column(name = "loan_application_number")
    private Integer loanApplicationNumber;

    @Column(name = "product_code",nullable = false)
    private String productCode;

    @Column(name = "loan_amount_requested",nullable = false)
    private Integer loanAmountRequested;

    @Column(name = "tenure",nullable = false)
    private Integer tenure;

    @Column(name = "rate",nullable = false)
    private Integer rate;

    @Column(name = "agreement_date",nullable = false)
    private LocalDate agreement_date;

    @Column(name = "installment_due_date",nullable = false)
    private LocalDate installmentDueDate;

    @Column(name = "create_date",nullable = false)
    private LocalDate createDate;

    @Column(name = "created_by", nullable = false, length = 30)
    private String createdBy;

    @Column(name = "modified_date",nullable = false)
    private LocalDate modifiedDate;

    @Column(name = "modified_by", nullable = false, length = 30)
    private String modifiedBy;

    @Column(name = "authorized_date",nullable = false)
    private LocalDate authorizedDate;

    @Column(name = "authorized_by", nullable = false, length = 30)
    private String authorizedBy;

    public Integer getLoanApplicationNumber() {
        return loanApplicationNumber;
    }

    public void setLoanApplicationNumber(Integer loanApplicationNumber) {
        this.loanApplicationNumber = loanApplicationNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public LocalDate getAgreement_date() {
        return agreement_date;
    }

    public void setAgreement_date(LocalDate agreement_date) {
        this.agreement_date = agreement_date;
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

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getLoanAmountRequested() {
        return loanAmountRequested;
    }

    public void setLoanAmountRequested(Integer loanAmountRequested) {
        this.loanAmountRequested = loanAmountRequested;
    }
}
