package com.nucleus.repaymentschedule.service;


import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.repaymentschedule.dao.RepaymentScheduleDAO;
import com.nucleus.repaymentschedule.dao.RepaymentScheduleDAOImpl;
import com.nucleus.repaymentschedule.model.RepaymentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as a Service layer for adding Repayment Schedule .
 *
 */
@Service
@Transactional
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



    /**
     * Adds a Repayment Schedule to the database.
     * @param loanApplication whose RepaymentSchedule is to be saved.
     * @return 1 if creation was successful, else 0.
     */
    @Override
    public int addRepaymentSchedule(LoanApplications loanApplication) {

        int loanApplicationNumber = loanApplication.getLoanApplicationNumber();
        double loanAmountRequested = loanApplication.getLoanAmountRequested();
        int tenure = loanApplication.getTenure();
        double rate = loanApplication.getRate();
        LocalDate installmentDueDate = loanApplication.getInstallmentDueDate();

        List<RepaymentSchedule> repaymentSchedules = generateRepaymentSchedule(loanApplication, rate, loanAmountRequested, tenure,
                installmentDueDate);

        int r = 0;
        for (int i = 0; i < repaymentSchedules.size(); i++) {
                repaymentScheduleDAO.addRepaymentSchedule(repaymentSchedules.get(i));
        }
        return r;
    }

    /**
     * calculate EMI for a particular loan
     * @param rate,loanAmount,tenure,numberOfInstallment
     * @return EMI amount
     */
    public double calculateEMI(double rate, double loanAmount, int tenure, int numberOfInstallment) {
        int n = numberOfInstallment * tenure;
        double r = (rate / (12 * 100));
        double num = loanAmount * r;
        double den = 1 - Math.pow(1 + r, -n);
        double installmentAmt = num / den;
        return installmentAmt;
    }

    /**
     * generate Repayment Schedule for a particular loan
     * @param loanApplicationNumber,rate,loanAmount,tenure,installmentDueDate
     * @return List of Repayment Schedule
     */
    public List<RepaymentSchedule> generateRepaymentSchedule(LoanApplications loanApplicationNumber, double rate, double loanAmount,
                                                             int tenure, LocalDate installmentDueDate) {
        int numberOfInstallment = 12; // Reapyment Frequency Assumed Monthly

        double installmentAmt = calculateEMI(rate, loanAmount, tenure, numberOfInstallment);
        int installmentNumber;
        double principalComp;
        double interestComp;
        double closingBalance;
        LocalDate today = LocalDate.now();
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
            if(installmentDueDate.compareTo(today)>0)
                repaymentSchedule.setBillFlag("N");
            else
                repaymentSchedule.setBillFlag("Y");

            repaymentSchedule.setDueDate(installmentDueDate);
            installmentDueDate = futureDate;

            repaymentSchedule.setEmi(Double.parseDouble(String.format("%.2f", installmentAmt)));

            openingBalance = closingBalance;
            repaymentSchedules.add(repaymentSchedule);
        }
        return repaymentSchedules;
    }
}