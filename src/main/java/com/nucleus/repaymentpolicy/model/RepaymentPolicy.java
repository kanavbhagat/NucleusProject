package com.nucleus.repaymentpolicy.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "repayment_policy")
public class RepaymentPolicy implements Serializable {

    @Id
    @Column(name = "POLICY_CODE",nullable=false,length=10, unique = true)
    @NotNull
    @Size(min=3, max=30, message="policy code is required")
    private String policyCode;

    @NotEmpty
    @Column(name = "POLICY_NAME",nullable=false,length=30, unique = true)
    private String policyName;

    @Column(name = "POLICY_DESCRIPTION",length = 200)
    private String policyDescription;

    @Column(name = "INSTALMENT_DUE_DATE",nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Column(name = "REPAYMENT_FREQUENCY",length = 20,nullable = false)
    private String repaymentFrequency;

    @Column(name = "MIN_TENURE")
    private int minTenure;

    @Column(name = "MAX_TENURE")
    private int maxTenure;

    @Column(name = "DEFAULT_TENURE")
    private int defaultTenure;

    @Column(name = "INTEREST_RATE_TYPE")
    private String interestRateType;

    @Column(name = "DEFAULT_RATE")
    private double defaultRate;

    @Column(name = "CREATED_DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createdDate;

    @Column(name = "CREATED_BY",length = 30)
    private String createdBy;

    @Column(name = "MODIFIED_DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate modifiedDate;

    @Column(name = "MODIFIED_BY",length = 30)
    private String modifiedBy;

    @Column(name = "AUTHORIZED_DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate authorizedDate;

    @Column(name = "AUTHORIZED_BY",length = 30)
    private String authorizedBy;

    @Column(name = "STATUS",length = 15)
    private String status;

    @Override
    public String toString() {
        return "NewRepaymentPolicy{" +
                "policyCode='" + policyCode + '\'' +
                ", policyName='" + policyName + '\'' +
                ", policyDescription='" + policyDescription + '\'' +
                ", date=" + date +
                ", repaymentFrequency='" + repaymentFrequency + '\'' +
                ", minTenure=" + minTenure +
                ", maxTenure=" + maxTenure +
                ", defaultTenure=" + defaultTenure +
                ", interestRateType='" + interestRateType + '\'' +
                ", defaultRate=" + defaultRate +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", authorizedDate=" + authorizedDate +
                ", authorizedBy='" + authorizedBy + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getInterestRateType() {
        return interestRateType;
    }

    public void setInterestRateType(String interestRateType) {
        this.interestRateType = interestRateType;
    }

    public double getDefaultRate() {
        return defaultRate;
    }

    public void setDefaultRate(double defaultRate) {
        this.defaultRate = defaultRate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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


/* DATA TABLE
        PolicyCode	Varchar2	10	Y
        PolicyName	Varchar2	30	Y	unique
        PolicyDescription	Varchar2	200	N
        InstallmentDueDate	date		Y	multivalued
        RepaymentFrequency	Varchar2	20	Y	Monthly, Quarterly
        MinTenure	number	3	N
        MaxTenure	number	3	N
        DefaultTenure	number	3	N	"DefaultTenure should be
        Between min and max tenure"
        InterestRateType	Varchar2	20	N	Fixed, Floating
        DefaultRate	number	2	N
        createDate	date		N
        createdBy	Varchar2	30	N
        modifiedDate	date		N
        modifiedBy	Varchar2	30	N
        authorizedDate	date		N
        authorizedBy	Varchar2	30	N

        */
