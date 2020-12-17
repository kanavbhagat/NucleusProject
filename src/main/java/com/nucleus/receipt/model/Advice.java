package com.nucleus.receipt.model;


import com.nucleus.loanaplications.model.LoanApplications;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="advice")
public class Advice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="advice_id",length = 10)
    private Integer adviceId;

    @Column(name = "amount_due",nullable = false)
    private double amountDue;

    @Column(name = "type", nullable=false,length = 20)
    private String type;

    @Column(name="status",nullable = false,length = 20)
    private String Status;

    @Column(name="advice_type",nullable = false,length = 20)
    private String adviceType;

    @Column(name="date",nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="loan_application_number",referencedColumnName ="loan_application_number" ,nullable = false)
    private LoanApplications loanApplicationNumber;

    @Column(name="installment_no",nullable = false,length=10)
    private Integer installmentNo;

    public Advice() {
    }

    public Integer getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(Integer adviceId) {
        this.adviceId = adviceId;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAdviceType() {
        return adviceType;
    }

    public void setAdviceType(String adviceType) {
        this.adviceType = adviceType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LoanApplications getLoanApplicationNumber() {
        return loanApplicationNumber;
    }

    public void setLoanApplicationNumber(LoanApplications loanApplicationNumber) {
        this.loanApplicationNumber = loanApplicationNumber;
    }

    public Integer getInstallmentNo() {
        return installmentNo;
    }

    public void setInstallmentNo(Integer installmentNo) {
        this.installmentNo = installmentNo;
    }
}
