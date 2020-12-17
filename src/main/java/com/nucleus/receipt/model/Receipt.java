package com.nucleus.receipt.model;


import com.nucleus.loanaplications.model.LoanApplications;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "receipts")
public class Receipt {

    @Id
    @Column(name = "receipt_no",length = 10,nullable = false)
    private Integer receiptNo;

    @Column(name="receipt_basis",length = 40,nullable = false)
    private String receiptBasis;

    @Column(name="receipt_type",length = 20,nullable = false)
    private String receiptType;

    @Column(name="date_of_receipt")
    private LocalDate dateOfReceipt;

    @Column(name = "receipt_amount",nullable = false)
    private double receiptAmount;

    @Column(name="receipt_purpose",length = 20)
    private String receiptPurpose;

    @ManyToOne
    @JoinColumn(name="loan_application_number",referencedColumnName ="loan_application_number" ,nullable = false)
    private LoanApplications loanApplicationNumber;

    @Column(name="create_date")
    private LocalDate createDate;

    @Column(name="created_by",length = 30)
    private String createdBy;

    @Column(name="modified_date")
    private LocalDate modifiedDate;

    @Column(name="modified_by",length = 30)
    private String modifiedBy;

    @Column(name="authorized_date")
    private LocalDate authorizedDate;

    @Column(name = "authorized_by",length = 30)
    private String authorizedBy;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "payment_mode",length = 30)
    private String paymentMode;

    @Column(name = "auto_allocation",length = 20)
    private String autoAllocation;


    public Receipt() {
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getAutoAllocation() {
        return autoAllocation;
    }

    public void setAutoAllocation(String autoAllocation) {
        this.autoAllocation = autoAllocation;
    }

    public Integer getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(Integer receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getReceiptBasis() {
        return receiptBasis;
    }

    public void setReceiptBasis(String receiptBasis) {
        this.receiptBasis = receiptBasis;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public LocalDate getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(LocalDate dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public double getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getReceiptPurpose() {
        return receiptPurpose;
    }

    public void setReceiptPurpose(String receiptPurpose) {
        this.receiptPurpose = receiptPurpose;
    }

    public LoanApplications getLoanApplicationNumber() {
        return loanApplicationNumber;
    }

    public void setLoanApplicationNumber(LoanApplications loanApplicationNumber) {
        this.loanApplicationNumber = loanApplicationNumber;
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


}
