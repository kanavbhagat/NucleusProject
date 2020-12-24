package com.nucleus.customer.model;

import com.nucleus.loanapplications.model.LoanApplications;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table( name= "customer")
public class Customer {

    //103
    public static  int id = 110;
    public static String code;
    public Customer(){
        super();
        code = "L"+id;
        this.setCustomerCode(code);
        add = new Address();
    }


    @Id
    @Column(name="customer_code",length = 20,nullable = false)
    private String customerCode;

    @Column(name="first_name",length = 30,nullable = false)
    @Size(min=3, max = 30, message = "First name must have 3 to 30 characters!")
    @Pattern(regexp = "^([A-Za-z]+)$", message = "Name cannot contain spaces or special characters!")
    private String firstName;

    @Column(name="last_name",length = 30,nullable = false)
    @Size(min=3, max = 30, message = "Last name must have 3 to 30 characters!")
    @Pattern(regexp = "^([A-Za-z]+)$", message = "Name cannot contain spaces or special characters!")
    private String lastName;

    @Column(name="date_of_birth" , nullable = false)
    @NotNull(message = "Please enter a Date")
    private LocalDate dateOfBirth;

    @Column(name="nationality",length = 30, nullable = false)
    @NotNull(message = "Please select a valid Nationality")
    private String nationality;

    @Column(name="occupation_type",length = 30, nullable = false)
    @NotNull(message = "Please select a valid Occupation type")
    private String occupationType;

    @Column(name="total_work_experience",length = 2)
    @NotEmpty(message = "Total Work experience cannot be empty")
    private Integer totalWorkExperience;

    @Column(name="organization_name",length = 30)
    @NotEmpty(message = "Organization name cannot be empty")
    private String organizationName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="customerCode")
    private List<LoanApplications> loanApplications;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="customerCode")
    private List<Address> addresses;

    @Transient
    private Address add;


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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
