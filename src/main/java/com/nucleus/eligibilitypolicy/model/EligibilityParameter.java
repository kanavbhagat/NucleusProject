package com.nucleus.eligibilitypolicy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "eligibility_parameters")
public class EligibilityParameter {

    @Id
    @Column(name = "parameter_code", length = 10)
    private String parameterCode;

    @Column(name = "parameter_name", length = 10, nullable = false, unique = true)
    private String parameterName;

    @Column(name = "min_value", nullable = false)
    private double minValue;

    @Column(name = "max_value")
    private double maxValue;

    @Column(name = "parameter_description", length = 200)
    private String parameterDescription;

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

    // Getters - Setters
    public String getParameterCode() {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public String getParameterDescription() {
        return parameterDescription;
    }

    public void setParameterDescription(String parameterDescription) {
        this.parameterDescription = parameterDescription;
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
        return "EligibilityParameter{" +
                "parameterCode='" + parameterCode + '\'' +
                ", parameterName='" + parameterName + '\'' +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", parameterDescription='" + parameterDescription + '\'' +
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
        EligibilityParameter that = (EligibilityParameter) o;
        return Double.compare(that.minValue, minValue) == 0 &&
                Double.compare(that.maxValue, maxValue) == 0 &&
                parameterCode.equals(that.parameterCode) &&
                parameterName.equals(that.parameterName) &&
                Objects.equals(parameterDescription, that.parameterDescription) &&
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
        return Objects.hash(parameterCode, parameterName, minValue, maxValue, parameterDescription, createDate, createdBy, modifiedDate, modifiedBy, authorizedDate, authorizedBy, status);
    }
}
