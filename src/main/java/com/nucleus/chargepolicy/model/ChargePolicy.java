package com.nucleus.chargepolicy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "charge_policy")
public class ChargePolicy {

    @Id
    @Column(name = "policy_code")
    String chargePolicyCode;

    @Column(name = "policy_name")
    String chargePolicyName;

    @Column(name = "policy_description")
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
    String chargeCode;

    @Column
    String chargeCodeName;

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
