package com.nucleus.loanclosurebod.database;

import com.nucleus.eligibilitypolicy.model.EligibilityParameter;
import com.nucleus.loanclosurebod.model.LoanApplication;
import com.nucleus.loanclosurebod.model.RepaymentSchedule;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public class LoanClosureDaoImpl implements LoanClosureDao{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException E){
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Override
    public int function1() {
        return 0;
    }

    @Override
    public List<LoanApplication> function2(int loanId) {
        return null;
    }

    @Override
    public void function3() {

    }

    @Override
    public void addDummyData() {
        Session session = getSession();
        session.beginTransaction();

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setLoanApplicationNumber(1);
        loanApplication.setLoanAmountRequested(100000);
        loanApplication.setTenure(5);
        loanApplication.setRate(20);
        loanApplication.setAgreementDate(LocalDate.of(2020, 06, 10));
        loanApplication.setAuthorizedBy("Apurv");
        loanApplication.setAuthorizedDate(LocalDate.of(2020, 06, 10));
        loanApplication.setCreateDate(LocalDate.of(2020, 06, 10));
        loanApplication.setInstallmentDueDate(LocalDate.of(2021, 06, 10));
//        session.save(loanApplication);

        RepaymentSchedule repaymentSchedule = new RepaymentSchedule();
        repaymentSchedule.setLoanApplicationn(loanApplication);
        repaymentSchedule.setInstallmentNumber(1);
        repaymentSchedule.setDueDate(LocalDate.of(2021, 06, 10));
        repaymentSchedule.setBillFlag("Y");
        repaymentSchedule.setOpeningBalance(30000);
        repaymentSchedule.setEMI(20000);
        repaymentSchedule.setClosingBalance(0);
        repaymentSchedule.setInterestComponent(0.2);
        repaymentSchedule.setPrincipalComponent(10000);
        session.save(repaymentSchedule);


//        EligibilityParameter eligibilityParameter1 = new EligibilityParameter();
//        eligibilityParameter1.setParameterName("Test 1");
//        eligibilityParameter1.setParameterDescription("Testing 1");
//        eligibilityParameter1.setParameterCode("101");
//        session.save(eligibilityParameter1);

//        EligibilityParameter eligibilityParameter2 = new EligibilityParameter();
//        eligibilityParameter2.setParameterName("Test 2");
//        eligibilityParameter2.setParameterDescription("Testing 2");
//        eligibilityParameter2.setParameterCode("102");
//        session.save(eligibilityParameter2);
        session.getTransaction().commit();
    }

    @Override
    public void updateStatus(){

    }
}
