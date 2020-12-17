package com.nucleus.eligibilitypolicy.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "eligibility_policies")
public class EligibilityPolicy {

    @Id
    @Column(name = "policy_code", length = 10)
    private String policyCode;

    @Column(name = "policy_name", length = 20, nullable = false, unique = true)
    private String policyName;

    @Column(name = "policy_description", length = 200)
    private String policyDescription;

    @ManyToMany
    @JoinTable(name = "eligibility_policy_mappings",
            joinColumns = {@JoinColumn(name="policy_code", referencedColumnName="policy_code")},
            inverseJoinColumns = {@JoinColumn(name="parameter_code", referencedColumnName="parameter_code")
    })
    private List<EligibilityParameter> eligibilityParameterList;

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

    @Column(name = "status", length = 20)
    private String status;

    //Getters - Setters
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

    public List<EligibilityParameter> getEligibilityParameterList() {
        return eligibilityParameterList;
    }

    public void setEligibilityParameterList(List<EligibilityParameter> eligibilityParameterList) {
        this.eligibilityParameterList = eligibilityParameterList;
    }

    @Override
    public String toString() {
        return "EligibilityPolicy{" +
                "policyCode='" + policyCode + '\'' +
                ", policyName='" + policyName + '\'' +
                ", policyDescription='" + policyDescription + '\'' +
                ", eligibilityParameterList=" + eligibilityParameterList +
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
        if (o == null || getClass() != o.getClass()) return false;
        EligibilityPolicy that = (EligibilityPolicy) o;
        return policyCode.equals(that.policyCode) &&
                policyName.equals(that.policyName) &&
                Objects.equals(policyDescription, that.policyDescription) &&
                Objects.equals(eligibilityParameterList, that.eligibilityParameterList) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(modifiedDate, that.modifiedDate) &&
                Objects.equals(modifiedBy, that.modifiedBy) &&
                Objects.equals(authorizedDate, that.authorizedDate) &&
                Objects.equals(authorizedBy, that.authorizedBy) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyCode, policyName, policyDescription, eligibilityParameterList, createDate, createdBy, modifiedDate, modifiedBy, authorizedDate, authorizedBy, status);
    }

}
