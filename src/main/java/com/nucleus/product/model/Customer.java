package com.nucleus.product.model;
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
    private int totalWorkExperience;

    @Column(name="organization_name",length = 30)
    private int organizationName;






}
