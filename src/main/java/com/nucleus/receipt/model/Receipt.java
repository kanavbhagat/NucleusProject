package com.nucleus.receipt.model;



import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.nucleus.loanapplications.model.LoanApplications;


import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Table(name = "receipts")
public class Receipt {

    @Id
    @Digits(fraction = 0, integer = 10, message = "Receipt Number must be an integer")
    @Min(value = 0, message = "Receipt Number must be positive")
    @NotNull(message = "Receipt Number cannot be blank")
    @Column(name = "receipt_no",length = 10,nullable = false)
    private Integer receiptNo;

    @NotBlank(message = "Receipt Basis cannot be blank")
    @Column(name="receipt_basis",length = 40, nullable = false)
    private String receiptBasis;

    @NotBlank(message = "Receipt Type cannot be blank")
    @Column(name="receipt_type",length = 20, nullable = false)
    private String receiptType;

    @NotNull(message = "Date of Receipt cannot be blank")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @Column(name="date_of_receipt")
    private LocalDate dateOfReceipt;

    @Digits(fraction = 0, integer = 10, message = "Receipt Amount must be an integer")
    @Min(value = 0, message = "Receipt Amount must be positive")
    @NotNull(message = "Receipt Amount cannot be blank")
    @Column(name = "receipt_amount",nullable = false)
    private Integer receiptAmount;

    @NotBlank(message = "Receipt Purpose cannot be blank")
    @Column(name="receipt_purpose",length = 20)
    private String receiptPurpose;

    @ManyToOne
    @JoinColumn(name="loan_application_number",referencedColumnName ="loan_application_number", nullable = false)
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

    @NotEmpty(message = "Payment Mode cannot be blank")
    @Column(name = "payment_mode",length = 30)
    private String paymentMode;

    @NotEmpty(message = "Select one")
    @Column(name = "auto_allocation",length = 20)
    private String autoAllocation;

    @NotNull(message = "Loan Application Number cannot be blank")
    @Digits(fraction = 0, integer = 10, message = "Loan Application Number must be an integer")
    @Min(value = 0, message = "Loan Application Number must be positive")
    private String loanApplicationValue;

    public String getLoanApplicationValue(){
        return loanApplicationValue;
    }

    public void setLoanApplicationValue(String loanApplicationValue) {
        this.loanApplicationValue = loanApplicationValue;
    }



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

    public Integer getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Integer receiptAmount) {
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
