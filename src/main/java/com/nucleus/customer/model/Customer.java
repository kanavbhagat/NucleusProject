package com.nucleus.customer.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
@Entity
@Table( name= "customer")
public class Customer {

    @Id
    @Column(name="customer_code",length = 20,nullable = false)
    private String customerCode;

    @Column(name="first_name",length = 30,nullable = false)
    private String firstName;

    @Column(name="last_name",length = 30,nullable = false)
    private String lastName;

    @Column(name="date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name="nationality",length = 30, nullable = false)
    private String nationality;

    @Column(name="occupation_type",length = 30, nullable = false)
    private String occupationType;

    @Column(name="total_work_experience",length = 2)
    private Integer totalWorkExperience;

    @Column(name="organization_name",length = 30)
    private Integer organizationName;


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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

    public Integer getTotalWorkExperience() {
        return totalWorkExperience;
    }

    public void setTotalWorkExperience(Integer totalWorkExperience) {
        this.totalWorkExperience = totalWorkExperience;
    }

    public Integer getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(Integer organizationName) {
        this.organizationName = organizationName;
    }
}
