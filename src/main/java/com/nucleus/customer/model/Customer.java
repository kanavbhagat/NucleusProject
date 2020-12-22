package com.nucleus.customer.model;

import com.nucleus.loanapplications.model.LoanApplications;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @Column(name="date_of_birth" , nullable = false)
    private String dateOfBirth;

    @Column(name="nationality",length = 30, nullable = false)
    private String nationality;

    @Column(name="occupation_type",length = 30, nullable = false)
    private String occupationType;

    @Column(name="total_work_experience",length = 2)
    private Integer totalWorkExperience;

    @Column(name="organization_name",length = 30)
    private String organizationName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="customerCode")
    private List<LoanApplications> loanApplications;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="customerCode")
    private List<Address> addresses;

    @Transient
    private Address add;

    public Customer(){
        super();
        add = new Address();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Address getAdd() {
        return add;
    }

    public void setAdd(Address add) {
        this.add = add;
    }

    public List<LoanApplications> getLoanApplications() {
        return loanApplications;
    }

    public void setLoanApplications(List<LoanApplications> loanApplications) {
        this.loanApplications = loanApplications;
    }

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


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
