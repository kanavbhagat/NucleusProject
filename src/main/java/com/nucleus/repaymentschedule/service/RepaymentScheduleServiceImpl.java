package com.nucleus.repaymentschedule.service;


import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.repaymentschedule.dao.RepaymentScheduleDAO;
import com.nucleus.repaymentschedule.model.RepaymentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RepaymentScheduleServiceImpl implements RepaymentScheduleService {

    @Autowired
    private RepaymentScheduleDAO repaymentScheduleDAO;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;


    @PostConstruct
    public void init() {
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    static LocalDate returnDate(String date) {
        LocalDate dt = LocalDate.parse(date);
        return dt;
    }


    @Override
    public int addRepaymentSchedule(LoanApplications loanApplication) {

        int loanApplicationNumber = loanApplication.getLoanApplicationNumber();
        System.out.println("loanApplicationNumber-----------"+loanApplicationNumber);
        double loanAmountRequested = loanApplication.getLoanAmountRequested();
        int tenure = loanApplication.getTenure();
        double rate = loanApplication.getRate();
        LocalDate installmentDueDate = loanApplication.getInstallmentDueDate();

        List<RepaymentSchedule> repaymentSchedules = generateRepaymentSchedule(loanApplication, rate, loanAmountRequested, tenure,
                installmentDueDate);
        System.out.println("repaymentSchedule-----------"+repaymentSchedules.size()+"--------------------------");

        int r = 0;
        for (int i = 0; i < repaymentSchedules.size(); i++) {
            System.out.println("i---------------------  "+i+"  ----------------");
            System.out.println("RepaymentSchedule=================="+repaymentSchedules.get(i).getBillFlag()+"----------------");

            repaymentScheduleDAO.addRepaymentSchedule(repaymentSchedules.get(i));

        }
        return r;
    }

    double calculateEMI(double rate, double loanAmount, int tenure, int numberOfInstallment) {
        int n = numberOfInstallment * tenure;
        double r = (rate / (12 * 100));
        double num = loanAmount * r;
        double den = 1 - Math.pow(1 + r, -n);
        double installmentAmt = num / den;
        return installmentAmt;
    }


    List<RepaymentSchedule> generateRepaymentSchedule(LoanApplications loanApplicationNumber, double rate, double loanAmount,
                                                      int tenure, LocalDate installmentDueDate) {
        int numberOfInstallment = 12; // Reapyment Frequency Assumed Monthly

        double installmentAmt = calculateEMI(rate, loanAmount, tenure, numberOfInstallment);
        System.out.println("installmentAmount-----------"+installmentAmt+"--------------");
        int installmentNumber;
        double principalComp;
        double interestComp;
        double closingBalance;
        int n = numberOfInstallment * tenure;
        double r = (rate / (12 * 100));
        double openingBalance = loanAmount;
        ArrayList<RepaymentSchedule> repaymentSchedules = new ArrayList<RepaymentSchedule>();

        for (int i = 0; i < n; i++) {

            installmentNumber = i + 1;
            interestComp = openingBalance * r;
            principalComp = installmentAmt - interestComp;
            closingBalance = openingBalance - principalComp;
            LocalDate futureDate = installmentDueDate.plusMonths(1);

            RepaymentSchedule repaymentSchedule = new RepaymentSchedule();

            repaymentSchedule.setLoanApplicationNumber(loanApplicationNumber);
            repaymentSchedule.setInstallmentNumber(installmentNumber);
            repaymentSchedule.setOpeningBalance(Double.parseDouble(String.format("%.2f", openingBalance)));
            repaymentSchedule.setInterestComponent(Double.parseDouble(String.format("%.2f", interestComp)));
            repaymentSchedule.setPrincipalComponent(Double.parseDouble(String.format("%.2f", principalComp)));
            repaymentSchedule.setClosingBalance(Double.parseDouble(String.format("%.2f", closingBalance)));
            repaymentSchedule.setBillFlag("F");
            repaymentSchedule.setDueDate(installmentDueDate);
            System.out.println("RepaymentSchedule=================="+repaymentSchedule.getBillFlag()+"----------------");
            installmentDueDate = futureDate;

            repaymentSchedule.setEmi(Double.parseDouble(String.format("%.2f", installmentAmt)));

            openingBalance = closingBalance;
            repaymentSchedules.add(repaymentSchedule);
        }
        return repaymentSchedules;
    }
}