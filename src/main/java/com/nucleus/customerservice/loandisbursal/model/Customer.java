package com.nucleus.customerservice.loandisbursal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.EnumType;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @Column(name = "customer_code", length = 20,nullable = false)
    private String customerCode;

    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    public enum OccupationType {
        Salaried, SelfEmployed;
    }

    public enum Nationality {
        Indian, Canadian, American;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "nationality", length = 30, nullable = false)
    private Nationality nationality;


    @Enumerated(EnumType.STRING)
    @Column(name = "occupation_type", length = 30, nullable = false)
    private OccupationType occupationType;

    @Column(name = "total_work_experience", length = 2)
    @Min(0)
    private int totalWorkExperience;

    @Column(name = "organization_name", length = 30)
    private String organizationName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="customerCode")
    private Set<LoanApplication> loanApplications;

    // Getter and setter
    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public OccupationType getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(OccupationType occupationType) {
        this.occupationType = occupationType;
    }

    public int getTotalWorkExperience() {
        return totalWorkExperience;
    }

    public void setTotalWorkExperience(int totalWorkExperience) {
        this.totalWorkExperience = totalWorkExperience;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Set<LoanApplication> getLoanApplications() {
        return loanApplications;
    }

    public void setLoanApplications(Set<LoanApplication> loanApplications) {
        this.loanApplications = loanApplications;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerCode='" + customerCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", nationality=" + nationality +
                ", occupationType=" + occupationType +
                ", totalWorkExperience=" + totalWorkExperience +
                ", organizationName='" + organizationName + '\'' +
                ", loanApplications=" + loanApplications +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customers = (Customer) o;
        return totalWorkExperience == customers.totalWorkExperience && customerCode.equals(customers.customerCode) && firstName.equals(customers.firstName) && lastName.equals(customers.lastName) && dateOfBirth.equals(customers.dateOfBirth) && nationality == customers.nationality && occupationType == customers.occupationType && Objects.equals(organizationName, customers.organizationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerCode, firstName, lastName, dateOfBirth, nationality, occupationType, totalWorkExperience, organizationName);
    }
}
