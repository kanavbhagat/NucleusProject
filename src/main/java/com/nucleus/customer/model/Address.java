package com.nucleus.customer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "house_no",nullable = false, length = 20)
    @NotNull(message = "House number cannot be empty")
    @Pattern(regexp = "^[1-9][0-9]*$", message = "Please enter a valid House number")
    private String houseNo;

    @Column(name = "city",nullable = false, length = 20)
    @NotNull(message = "Please select a valid City")
    private String city;

    @Column(name = "state",nullable = false,  length = 20)
    @NotNull(message = "Please select a valid State")
    private String state;

    @Column(name = "country",nullable = false, length = 20)
    @NotNull(message = "Please select a valid Country")
    private String country;

    @Column(name = "pinCode", nullable = false)
    @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Please enter a valid 6 digit Pin code")
    private Integer pinCode;


    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_code", referencedColumnName = "customer_code",nullable = false)
    private Customer customerCode;

    public Customer getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Customer customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pincode) {
        this.pinCode = pincode;
    }

}
