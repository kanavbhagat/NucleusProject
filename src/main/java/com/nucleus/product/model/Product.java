package com.nucleus.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_code", length = 10)
    private String productCode;

    @Column(name = "product_name", length = 30, nullable = false, unique = true)
    private String productName;

    @Column(name = "product_description", length = 200)
    private String productDescription;

    @Column(name = "product_type", length = 30, nullable = false)
    private String productType;

    @Column(name = "max_exposure_amount")
    private Integer maxExposureAmount;

    @Column(name = "repayment_policy_code", length = 10, nullable = false)
    private String repaymentPolicyCode;

    @Column(name = "eligibility_policy_code", length = 10, nullable = false)
    private String eligibilityPolicyCode;

    @Column(name = "charge_code_policy", length = 10)
    private String chargeCodePolicy;

    // TODO: 14/12/20 mapping for LocalDates
    private LocalDate createDate;

    @Column(name = "created_by", length = 30)
    private String createdBy;

    // TODO: 14/12/20 mapping for localdates
    private LocalDate modifiedDate;

    @Column(name = "modified_by", length = 30)
    private String modifiedBy;

    // TODO: 14/12/20
    private LocalDate authorizedDate;

    @Column(name = "authorized_date", length = 30)
    private String authorizedBy;

    @Column(name = "status", length = 30)
    private String status;
}
