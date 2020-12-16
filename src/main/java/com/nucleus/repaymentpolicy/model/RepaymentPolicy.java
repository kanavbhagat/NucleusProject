package com.nucleus.repaymentpolicy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "repayment_policy")
public class RepaymentPolicy {

    @Id
    @Column(name="policy_code",length = 10,nullable = false)
    private String policyCode;

    @Column(name="policy_name",length = 30,nullable = false,unique = true)
    private String policyName;

    @Column(name = "policy_description", length = 200)
    private String policyDescription;

//    @Column(name = "installment_due_date",nullable = false)
//    private LocalDate installmentDueDate;

//    @Column(name = "repayment_frequency",length = 20,nullable = false)
//    // Validation - Monthly , Quarterly
//    private String repaymentFrequency;
//
//    @Column(name = "min_tenure",length = 3)
//    private int minTenure;
//
//    @Column(name = "max_tenure",length = 3)
//    private int maxTenure;
//
//    //@Range
//    @Column(name = "default_tenure",length = 3 )
//    private int defaultTenure;
//
//    @Column(name = "created_date")
//    private LocalDate createdDate;

    @Column(name = "created_by",length = 30)
    private String createdBy;

//    @Column(name = "modified_date")
//    private LocalDate modifiedDate;

    @Column(name = "modified_by",length = 30)
    private String modifiedBy;

//    @Column(name = "authorized_date")
//    private LocalDate authorizedDate;

    @Column(name = "authorized_by",length = 30)
    private String authorizedBy;

}
