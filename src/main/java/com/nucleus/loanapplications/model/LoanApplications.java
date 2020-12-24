package com.nucleus.loanapplications.model;

import com.nucleus.customer.model.Customer;
import com.nucleus.product.model.Product;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "loan_applications")
public class LoanApplications {

    @Id
    @Column(name = "loan_application_number")
    /* @GeneratedValue(strategy=GenerationType.AUTO)*/
    private Integer loanApplicationNumber;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_code", referencedColumnName = "customer_code", nullable = false)
    private Customer customerCode;

    public Customer getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Customer customerCode) {
        this.customerCode = customerCode;
    }


    //set it to false optional and nullable
    @ManyToOne(optional = true)
    @JoinColumn(name = "product_code", referencedColumnName = "product_code",nullable = true)
    private Product productCode;

    @Column(name = "loan_amount_requested",nullable = false)
    @NotEmpty(message = "Loan amount requested cannot be empty")
    private Integer loanAmountRequested;

    @Column(name = "tenure",nullable = false)
    @NotEmpty(message = "Tenure cannot be empty")
    //Enter pattern
    private Integer tenure;

    @Column(name = "rate",nullable = false)
    @NotEmpty(message = "Rate cannot be empty")
    private Double rate;

    @Column(name = "agreement_date",nullable = false)
    @NotNull(message = "Agreement Date cannot be empty")
    private LocalDate agreementDate;

    @Column(name = "installment_due_date",nullable = false)
    @NotNull(message = "Installment Due Date cannot be empty")
    private LocalDate installmentDueDate;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "created_by", length = 30)
    private String createdBy;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "modified_by",  length = 30)
    private String modifiedBy;

    @Column(name = "authorized_date")
    private LocalDate authorizedDate;

    @Column(name = "authorized_by", length = 30)
    private String authorizedBy;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "status",nullable = false, length = 10)
    private String status;

    public Integer getLoanApplicationNumber() {
        return loanApplicationNumber;
    }

    public void setLoanApplicationNumber(Integer loanApplicationNumber) {
        this.loanApplicationNumber = loanApplicationNumber;
    }

    public Product getProductCode() {
        return productCode;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDate getAgreementDate() {
        return agreementDate;
    }

    public void setAgreementDate(LocalDate agreement_date) {
        this.agreementDate = agreement_date;
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

    public void setProductCode(Product productCode) {
        this.productCode = productCode;
    }

    public Integer getLoanAmountRequested() {
        return loanAmountRequested;
    }

    public void setLoanAmountRequested(Integer loanAmountRequested) {
        this.loanAmountRequested = loanAmountRequested;
    }

    @Transient
    private String productType;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
