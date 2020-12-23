package com.nucleus.loanclosurebod.model;

import com.nucleus.loanapplications.model.LoanApplications;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table( name= "repayment_schedules")
public class RepaymentSchedule {

    @ManyToOne
    @JoinColumn(name="loan_application_number", referencedColumnName="loan_application_number", foreignKey=@ForeignKey(name="loan_FK"))
    private LoanApplications loanApplication;

    @Id
    @Column(name="installment_number", length = 10)
    private int installmentNumber;

    @Column(name="opening_balance")
    private double OpeningBalance;

    @Column(name="interest_component")
    private double InterestComponent;

    @Column(name="principal_component")
    private double PrincipalComponent;

    @Column(name="emi")
    private double EMI;

    @Column(name="closing_balance")
    private double ClosingBalance;

    @Column(name="due_date")
    private LocalDate DueDate;

    @Column(name="bill_flag")
    private String BillFlag;

    public LoanApplications getLoanApplicationn() {
        return loanApplication;
    }

    public void setLoanApplicationn(LoanApplications loanApplication) {
        this.loanApplication = loanApplication;
    }

    public int getInstallmentNumber() {
        return installmentNumber;
    }

    public void setInstallmentNumber(int installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public double getOpeningBalance() {
        return OpeningBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        OpeningBalance = openingBalance;
    }

    public double getInterestComponent() {
        return InterestComponent;
    }

    public void setInterestComponent(double interestComponent) {
        InterestComponent = interestComponent;
    }

    public double getPrincipalComponent() {
        return PrincipalComponent;
    }

    public void setPrincipalComponent(double principalComponent) {
        PrincipalComponent = principalComponent;
    }

    public double getEMI() {
        return EMI;
    }

    public void setEMI(double EMI) {
        this.EMI = EMI;
    }

    public double getClosingBalance() {
        return ClosingBalance;
    }

    public void setClosingBalance(double closingBalance) {
        ClosingBalance = closingBalance;
    }

    public LocalDate getDueDate() {
        return DueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        DueDate = dueDate;
    }

    public String getBillFlag() {
        return BillFlag;
    }

    public void setBillFlag(String billFlag) {
        BillFlag = billFlag;
    }

    @Override
    public String toString() {
        return "RepaymentSchedule{" +
                "installmentNumber=" + installmentNumber +
                ", OpeningBalance=" + OpeningBalance +
                ", InterestComponent=" + InterestComponent +
                ", PrincipalComponent=" + PrincipalComponent +
                ", EMI=" + EMI +
                ", ClosingBalance=" + ClosingBalance +
                ", DueDate=" + DueDate +
                ", BillFlag='" + BillFlag + '\'' +
                '}';
    }
}