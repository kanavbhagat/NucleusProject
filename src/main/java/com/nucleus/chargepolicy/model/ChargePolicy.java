package com.nucleus.chargepolicy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "charge_policy")
public class ChargePolicy {

    @Id
    String chargePolicyCode;

    @Column
    String chargePolicyName;

    @Column
    String chargePolicyDesc;

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
