package com.nucleus.loanclosurebod.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table( name= "repayment_schedule")
public class RepaymentSchedule {

    @OneToOne
    private LoanApplication loanApplicationn;

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

    public LoanApplication getLoanApplicationn() {
        return loanApplicationn;
    }

    public void setLoanApplicationn(LoanApplication loanApplicationn) {
        this.loanApplicationn = loanApplicationn;
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
                "loanApplicationn=" + loanApplicationn +
                ", installmentNumber=" + installmentNumber +
                ", OpeningBalance=" + OpeningBalance +
                ", InterestComponent=" + InterestComponent +
                ", PrincipalComponent=" + PrincipalComponent +
                ", EMI=" + EMI +
                ", ClosingBalance=" + ClosingBalance +
                ", DueDate='" + DueDate + '\'' +
                ", BillFlag='" + BillFlag + '\'' +
                '}';
    }


}
