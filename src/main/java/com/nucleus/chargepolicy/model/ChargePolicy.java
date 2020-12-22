package com.nucleus.chargepolicy.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "charge_policy")
public class ChargePolicy {

    @Id
    @Column(name = "policy_code")
    @NotEmpty(message = "Charge Policy Code cannot be empty")
    @Pattern(regexp = "^([A-Za-z0-9_]+)$", message = "Charge Policy Code cannot contain spaces or special characters!")
    @Size(max = 10, message = "Charge Policy Code must not have more than 10 characters!")
    String chargePolicyCode;

    @Column(name = "policy_name")
    @NotEmpty(message = "Charge Policy Name cannot be empty")
    String chargePolicyName;

    @Column(name = "policy_description")
    @NotEmpty(message = "Charge Policy Description cannot be empty")
    @Size(max = 180,  message = "Charge Policy Description cannot have more than 180 characters!")
    String chargePolicyDesc;

    @Column(name = "created_date")
    String createdDate;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "modified_date")
    String modifiedDate;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "authorized_date")
    String authorizedDate;

    @Column(name = "authorized_by")
    String authorizedBy;

    @Column
    String status;

    @Column
    String chargeCode;

    @Column
    @NotEmpty(message = "Please select Charge Code")
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getAuthorizedDate() {
        return authorizedDate;
    }

    public void setAuthorizedDate(String authorizedDate) {
        this.authorizedDate = authorizedDate;
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

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    public String getChargeCodeName() {
        return chargeCodeName;
    }

    public void setChargeCodeName(String chargeCodeName) {
        this.chargeCodeName = chargeCodeName;
    }


}
