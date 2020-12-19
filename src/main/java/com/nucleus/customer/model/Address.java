package com.nucleus.customer.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "house_no",nullable = false, length = 20)
    private String houseNo;

    @Column(name = "city",nullable = false, length = 20)
    private String city;

    @Column(name = "state",nullable = false,  length = 20)
    private String state;

    @Column(name = "country",nullable = false, length = 20)
    private String country;

    @Column(name = "pincode", nullable = false)
    private Integer pincode;


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

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

}
