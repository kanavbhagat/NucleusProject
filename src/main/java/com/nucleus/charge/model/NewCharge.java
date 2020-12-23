package com.nucleus.charge.model;


import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="charges")
public class NewCharge {

    @NotEmpty(message = "Charge code cannot be empty.")
    @Id
    @Column(name="charge_code", nullable = false, length = 10)
    private String chargeCode;

    @NotEmpty(message = "Charge Code Name cannot be empty.")
    @Pattern(regexp="^[a-zA-Z ]+$", message="Only Alphabets are allowed")
    @Column(name = "charge_code_name", unique = true, nullable = false, length = 20)
    private String chargeCodeName;

    @Column(name = "charge_policy_desc", length = 200)
    private String chargeDescription;

    @NotEmpty(message = "Please select a Transaction Event")
    @Column(name = "transaction_event", length = 40)
    private String transactionEvent;

    @NotEmpty(message = "Please select charge payment type")
    @Column(name = "charge_payment_type", nullable = false, length = 20)
    private String chargePaymentType;

    @NotEmpty(message = "Please select a charge type")
    @Column(name = "charge_type", nullable = false, length = 20)
    private String chargeType;

    @Digits(integer = 10, fraction = 2, message = "Charge Amount should be of 0000000000.00")
    @DecimalMin(value = "0.0",message = "Charge cannot be negative")
    @Column(name = "charge_amount")
    private double chargeAmount;

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

    @Column(name = "status", length = 50)
    private String status;

    //Getters - Setters

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NewCharge{" +
                "chargeCode='" + chargeCode + '\'' +
                ", chargeCodeName='" + chargeCodeName + '\'' +
                ", chargeDescription='" + chargeDescription + '\'' +
                ", transactionEvent='" + transactionEvent + '\'' +
                ", chargePaymentType='" + chargePaymentType + '\'' +
                ", chargeType='" + chargeType + '\'' +
                ", chargeAmount=" + chargeAmount +
                ", createDate=" + createDate +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", authorizedDate=" + authorizedDate +
                ", authorizedBy='" + authorizedBy + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewCharge)) return false;
        NewCharge newCharge = (NewCharge) o;
        return Double.compare(newCharge.chargeAmount, chargeAmount) == 0 &&
                chargeCode.equals(newCharge.chargeCode) &&
                chargeCodeName.equals(newCharge.chargeCodeName) &&
                Objects.equals(chargeDescription, newCharge.chargeDescription) &&
                transactionEvent.equals(newCharge.transactionEvent) &&
                chargePaymentType.equals(newCharge.chargePaymentType) &&
                chargeType.equals(newCharge.chargeType) &&
                Objects.equals(createDate, newCharge.createDate) &&
                Objects.equals(createdBy, newCharge.createdBy) &&
                Objects.equals(modifiedDate, newCharge.modifiedDate) &&
                Objects.equals(modifiedBy, newCharge.modifiedBy) &&
                Objects.equals(authorizedDate, newCharge.authorizedDate) &&
                Objects.equals(authorizedBy, newCharge.authorizedBy) &&
                Objects.equals(status, newCharge.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chargeCode, chargeCodeName, chargeDescription, transactionEvent, chargePaymentType, chargeType, chargeAmount, createDate, createdBy, modifiedDate, modifiedBy, authorizedDate, authorizedBy, status);
    }
}
