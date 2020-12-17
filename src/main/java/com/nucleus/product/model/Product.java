package com.nucleus.product.model;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.repaymentpolicy.model.RepaymentPolicy;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {
    // TODO: 15/12/20 declare foreign keys once all entities are made.
    @Id
    @Column(name = "product_code", length = 10)
    private String productCode;

    @Column(name = "product_name", length = 30, nullable = false, unique = true)
    private String productName;

    @Column(name = "product_description", length = 200)
    private String productDescription;

    @Column(name = "product_type", length = 30, nullable = false)
    private String productType;

    @Column(name = "max_exposure_amount")
    private Integer maxExposureAmount;

    @ManyToOne
    @JoinColumn(name = "repayment_policy_code", referencedColumnName = "policy_code", nullable = false)
    private RepaymentPolicy repaymentPolicyCode;

    @ManyToOne
    @JoinColumn(name = "eligibility_policy_code", referencedColumnName = "policy_code", nullable = false)
    private EligibilityPolicy eligibilityPolicyCode;

    @Column(name = "charge_code_policy", length = 10)
    private String chargeCodePolicy;

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

    @Column(name = "status", length = 30)
    private String status;

    private String eligibilityPolicyCodeString;

    public Product(){
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getMaxExposureAmount() {
        return maxExposureAmount;
    }

    public void setMaxExposureAmount(Integer maxExposureAmount) {
        this.maxExposureAmount = maxExposureAmount;
    }

    public RepaymentPolicy getRepaymentPolicyCode() {
        return repaymentPolicyCode;
    }

    public void setRepaymentPolicyCode(RepaymentPolicy repaymentPolicyCode) {
        this.repaymentPolicyCode = repaymentPolicyCode;
    }

    public String getEligibilityPolicyCodeString() {
        if(eligibilityPolicyCode==null){
            return null;
        }
        return eligibilityPolicyCode.getPolicyCode();
    }

    public EligibilityPolicy getEligibilityPolicyCode() {
        return this.eligibilityPolicyCode;
    }

    public void setEligibilityPolicyCode(EligibilityPolicy eligibilityPolicyCode) {
        this.eligibilityPolicyCode = eligibilityPolicyCode;
    }

    public void setEligibilityPolicyCodeString(String eligibilityPolicyCode) {
        EligibilityPolicy policy = new EligibilityPolicy();
        policy.setPolicyCode(eligibilityPolicyCode);
        this.eligibilityPolicyCode = policy;
    }

    public String getChargeCodePolicy() {
        return chargeCodePolicy;
    }

    public void setChargeCodePolicy(String chargeCodePolicy) {
        this.chargeCodePolicy = chargeCodePolicy;
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
}
