package com.nucleus.chargepolicy.model;

import com.nucleus.charge.model.NewCharge;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "charge_policy")
public class ChargePolicy {

    @Id
    @Column(name = "policy_code",length = 10)
    @NotEmpty(message = "Charge Policy Code cannot be empty")
    @Pattern(regexp = "^([A-Za-z0-9_]+)$", message = "Charge Policy Code cannot contain spaces or special characters!")
    @Size(max = 10, message = "Charge Policy Code must not have more than 10 characters!")
    String chargePolicyCode;

    @Column(name = "policy_name", length = 20)
    @Pattern(regexp = "^([A-Za-z0-9_]+)$", message = "Charge Policy name cannot contain spaces or special characters!")
    @Size( max = 20,  message = "Charge Policy name must have a maximum of 20 characters!")
    @NotEmpty(message = "Charge Policy Name cannot be empty")
    String chargePolicyName;

    @Column(name = "policy_description", length = 200)
    @Size(max = 200,  message = "Charge Policy Description cannot have more than 200 characters!")
    String chargePolicyDesc;

    @Column(name = "created_date")
    LocalDate createdDate;

    @Column(name = "created_by", length = 30)
    String createdBy;

    @Column(name = "modified_date")
    LocalDate modifiedDate;

    @Column(name = "modified_by", length = 30)
    String modifiedBy;

    @Column(name = "authorized_date")
    LocalDate authorizedDate;

    @Column(name = "authorized_by", length = 30)
    String authorizedBy;

    @Column
    String status;


    @OneToOne
    @JoinColumn(name = "charge_code",referencedColumnName="charge_code")
    NewCharge charge;

    @Transient
    String chargeCodeName;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChargePolicyCode() {
        return chargePolicyCode;
    }

    public void setChargePolicyCode(String chargePolicyCode) {
        this.chargePolicyCode = chargePolicyCode;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public LocalDate getAuthorizedDate() {
        return authorizedDate;
    }

    public void setAuthorizedDate(LocalDate authorizedDate) {
        this.authorizedDate = authorizedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }


    public String getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public String getChargePolicyName() {
        return chargePolicyName;
    }

    public void setChargePolicyName(String chargePolicyName) {
        this.chargePolicyName = chargePolicyName;
    }

    public String getChargePolicyDesc() {
        return chargePolicyDesc;
    }

    public void setChargePolicyDesc(String chargePolicyDesc) {
        this.chargePolicyDesc = chargePolicyDesc;
    }


    public String getChargeCodeName() {
        return chargeCodeName;
    }

    public void setChargeCodeName(String chargeCodeName) {
        this.chargeCodeName = chargeCodeName;
    }

    public NewCharge getCharge() {
        return charge;
    }

    public void setCharge(NewCharge charge) {
        this.charge = charge;
    }

}
