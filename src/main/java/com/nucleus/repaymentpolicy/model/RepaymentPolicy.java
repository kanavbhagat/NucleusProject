package com.nucleus.repaymentpolicy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "repayment_policy")
public class RepaymentPolicy implements Serializable {

    @Id
    @Column(name="policy_code",length = 10,nullable = false)
    private String policyCode;

    @Column(name="policy_name",length = 30,nullable = false,unique = true)
    private String policyName;

    @Column(name = "policy_description", length = 200)
    private String policyDescription;

    @Column(name = "installment_due_date",nullable = false)
    private LocalDate installmentDueDate;

    @Column(name = "repayment_frequency",length = 20,nullable = false)
    // Validation - Monthly , Quarterly
    private String repaymentFrequency;

    @Column(name = "min_tenure",length = 3)
    private int minTenure;

    @Column(name = "max_tenure",length = 3)
    private int maxTenure;

    //@Range
    @Column(name = "default_tenure",length = 3 )
    private int defaultTenure;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "created_by",length = 30)
    private String createdBy;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "modified_by",length = 30)
    private String modifiedBy;

    @Column(name = "authorized_date")
    private LocalDate authorizedDate;

    @Column(name = "authorized_by",length = 30)
    private String authorizedBy;

    public String getPolicyCode() {
        return policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyDescription() {
        return policyDescription;
    }

    public void setPolicyDescription(String policyDescription) {
        this.policyDescription = policyDescription;
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

    public LocalDate getInstallmentDueDate() {
        return installmentDueDate;
    }

    public void setInstallmentDueDate(LocalDate installmentDueDate) {
        this.installmentDueDate = installmentDueDate;
    }

    public String getRepaymentFrequency() {
        return repaymentFrequency;
    }

    public void setRepaymentFrequency(String repaymentFrequency) {
        this.repaymentFrequency = repaymentFrequency;
    }

    public int getMinTenure() {
        return minTenure;
    }

    public void setMinTenure(int minTenure) {
        this.minTenure = minTenure;
    }

    public int getMaxTenure() {
        return maxTenure;
    }

    public void setMaxTenure(int maxTenure) {
        this.maxTenure = maxTenure;
    }

    public int getDefaultTenure() {
        return defaultTenure;
    }

    public void setDefaultTenure(int defaultTenure) {
        this.defaultTenure = defaultTenure;
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

    @Override
    public String toString() {
        return "RepaymentPolicy{" +
                "policyCode='" + policyCode + '\'' +
                ", policyName='" + policyName + '\'' +
                ", policyDescription='" + policyDescription + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", authorizedBy='" + authorizedBy + '\'' +
                '}';
    }
}
