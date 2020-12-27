package com.nucleus.eligibilitypolicy.model;

import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "eligibility_policies")
public class EligibilityPolicy implements Serializable {

    @NotEmpty(message = "Policy code must not be empty!")
    @Pattern(regexp = "^([A-Za-z0-9_]+)$", message = "Policy code cannot contain spaces or special characters!")
    @Size(max = 10, message = "Policy code must not have more than 10 characters!")
    @Id
    @Column(name = "policy_code", length = 10)
    private String policyCode;

    @NotEmpty(message = "Policy name must not be empty!")
    @Size(min = 2, max = 20,  message = "Policy name must have at least 2 and maximum 20 characters!")
    @Pattern(regexp = "^([A-Za-z0-9_]+)$", message = "Policy name cannot contain spaces or special characters!")
    @Column(name = "policy_name", length = 20, nullable = false, unique = true)
    private String policyName;

    @Size(max = 200,  message = "Policy description cannot have more than 200 characters!")
    @Column(name = "policy_description", length = 200)
    private String policyDescription;

    @ManyToMany(fetch = FetchType.EAGER)
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

    @Transient
    private String[] eligibilityParameterCodes;

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

    public List<EligibilityParameter> getEligibilityParameterList() {
        return eligibilityParameterList;
    }

    public void setEligibilityParameterList(List<EligibilityParameter> eligibilityParameterList) {
        this.eligibilityParameterList = eligibilityParameterList;
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

    public String[] getEligibilityParameterCodes() {
        return eligibilityParameterCodes;
    }

    public void setEligibilityParameterCodes(String[] eligibilityParameterCodes) {
        this.eligibilityParameterCodes = eligibilityParameterCodes;
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
