package com.nucleus.repaymentschedule.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "repayment_schedule")
public class RepaymentSchedule implements Serializable {

    @Id
    @Column(name = "loan_application_number", length = 10, nullable = false)

//    @OneToOne
//    @JoinColumn(name = "loan_application_number", referencedColumnName = "loan_application_number", nullable = false)
    private int loanApplicationNumber;

    @Id
    @Column(name = "installment_number", length = 10, nullable = false)
    private int installmentNumber;

    @Column(name = "opening_balance", nullable = false)
    private double openingBalance;

    @Column(name = "interest_component", nullable = false)
    private double interestComponent;

    @Column(name = "principal_component", nullable = false)
    private double principalComponent;

    @Column(name = "emi")
    private double emi;


    @Column(name = "closing_balance", nullable = false)
    private double closingBalance;


    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Override
    public String toString() {
        return "RepaymentSchedules{" +
                "loanApplicationNumber=" + loanApplicationNumber +
                ", installmentNumber=" + installmentNumber +
                ", openingBalance=" + openingBalance +
                ", interestComponent=" + interestComponent +
                ", principalComponent=" + principalComponent +
                ", emi=" + emi +
                ", closingBalance=" + closingBalance +
                ", dueDate='" + dueDate + '\'' +
                ", billFlag='" + billFlag + '\'' +
                '}';
    }

    public int getLoanApplicationNumber() {
        return loanApplicationNumber;
    }

    public void setLoanApplicationNumber(int loanApplicationNumber) {
        this.loanApplicationNumber = loanApplicationNumber;
    }

    public int getInstallmentNumber() {
        return installmentNumber;
    }

    public void setInstallmentNumber(int installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public double getInterestComponent() {
        return interestComponent;
    }

    public void setInterestComponent(double interestComponent) {
        this.interestComponent = interestComponent;
    }

    public double getPrincipalComponent() {
        return principalComponent;
    }

    public void setPrincipalComponent(double principalComponent) {
        this.principalComponent = principalComponent;
    }

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }

    public double getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(double closingBalance) {
        this.closingBalance = closingBalance;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getBillFlag() {
        return billFlag;
    }

    public void setBillFlag(String billFlag) {
        this.billFlag = billFlag;
    }

    @Column(name = "bill_flag", nullable = false, length = 1)
    private String billFlag;


}


