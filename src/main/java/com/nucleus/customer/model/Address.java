package com.nucleus.customer.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Pattern(regexp = "^[1-9][0-9]*$" , message = "Please enter a valid house number")
    private String houseNo;

    @Column(name = "city",nullable = false, length = 20)
    private String city;

    @Column(name = "state",nullable = false,  length = 20)
    private String state;

    @Column(name = "country",nullable = false, length = 20)
    private String country;

    @Column(name = "pinCode", nullable = false)
    @Min(value = 100000,message = "Please Enter a 6 digit pin code")
    @Max(value = 999999)
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
