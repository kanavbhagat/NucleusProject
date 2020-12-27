//import com.nucleus.config.TestConfig;
//import com.nucleus.customerservice.repaymentschedulereport.service.RepaymentScheduleReportServiceImpl;
//import com.nucleus.loanapplications.model.LoanApplications;
//import com.nucleus.repaymentschedule.model.RepaymentSchedule;
//import com.nucleus.repaymentschedule.service.RepaymentScheduleServiceImpl;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = TestConfig.class)
//public class RepaymentScheduleTest {
//    @Autowired
//    RepaymentScheduleReportServiceImpl getService;
//
//    @Autowired
//    RepaymentScheduleServiceImpl addService;
//
//    static LocalDate returnDate(String date) {
//        LocalDate dt = LocalDate.parse(date);
//        return dt;
//    }
//
//
//
//
////    @Test
////    public void getRepaymentScheduleByIdforPositiveCase(){
////        LoanApplications loanApplication =new LoanApplications();
////        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
////        loanApplication.setLoanAmountRequested(1000000);
////        loanApplication.setLoanApplicationNumber(10000);
////        loanApplication.setTenure(5);
////        loanApplication.setRate(10.75);
////
////        addService.addRepaymentSchedule(loanApplication);
////
////
////        final int id=10000;
////        final List<RepaymentSchedule> expected= getService.getRepaymentScheduleReport(id);
////        assertEquals(expected.get(0).getLoanApplicationNumber(), loanApplication.getLoanApplicationNumber());
////
////    }
//
////    @Test
////    public void getRepaymentScheduleByIdforNegativeCase(){
////        LoanApplications loanApplication =new LoanApplications();
////        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
////        loanApplication.setLoanAmountRequested(1000000);
////        loanApplication.setLoanApplicationNumber(10001);
////        loanApplication.setTenure(5);
////        loanApplication.setRate(10.75);
////
////        addService.addRepaymentSchedule(loanApplication);
////
////
////        assertFalse(getService.getRepaymentScheduleReport(10001).isEmpty());
////
////
////    }
//
//    @Test
//    public void randomRepaymentScheduleByIdforNegativeCase1(){
//        List<RepaymentSchedule> list=getService.getRepaymentScheduleReport(9999999);
//        assertEquals(0,list.size());
//    }
//
////    @Test
////    public void addRepaymentScheduleforPositiveCase()
////    {
////        LoanApplications loanApplication =new LoanApplications();
////        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
////        loanApplication.setLoanAmountRequested(1000000);
////        loanApplication.setLoanApplicationNumber(10002);
////        loanApplication.setTenure(5);
////        loanApplication.setRate(10.75);
////        addService.addRepaymentSchedule(loanApplication);
////
////        assertNotNull(getService.getRepaymentScheduleReport(10002));
////
////    }
//
////    @Test
////    public void addRepaymentScheduleforNegativeCase()
////    {
////        LoanApplications loanApplication =new LoanApplications();
////        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
////        loanApplication.setLoanAmountRequested(1000000);
////        loanApplication.setLoanApplicationNumber(10003);
////        loanApplication.setTenure(5);
////        loanApplication.setRate(10.75);
////        addService.addRepaymentSchedule(loanApplication);
////
////        assertNull(getService.getRepaymentScheduleReport(99999));
////
////    }
//
//    @Test
//    public void testEmiCalculationforPositiveCase(){
//        LoanApplications loanApplication =new LoanApplications();
//        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
//        loanApplication.setLoanAmountRequested(1000000);
//        loanApplication.setLoanApplicationNumber(10000);
//        loanApplication.setTenure(5);
//        loanApplication.setRate(10.75);
//        int numberOfInstallment = 12;
//        double emi= addService.calculateEMI(loanApplication.getRate(),loanApplication.getLoanAmountRequested(),
//                loanApplication.getTenure(),numberOfInstallment);
//        assertEquals(String.valueOf(21617.953681423394) ,String.valueOf(emi));
//    }
//
//    @Test
//    public void testEmiCalculationforNegativeCase(){
//        LoanApplications loanApplication =new LoanApplications();
//        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
//        loanApplication.setLoanAmountRequested(1000000);
//        loanApplication.setLoanApplicationNumber(10000);
//        loanApplication.setTenure(5);
//        loanApplication.setRate(10.75);
//        int numberOfInstallment = 12;
//        double emi= addService.calculateEMI(loanApplication.getRate(),loanApplication.getLoanAmountRequested(),
//                loanApplication.getTenure(),numberOfInstallment);
//        assertNotEquals(String.valueOf(551617.95) ,String.valueOf(emi));
//    }
//
//
//    @Test
//    public void testEmiCalculationforNegativeCase1(){
//        LoanApplications loanApplication =new LoanApplications();
//        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
//        loanApplication.setLoanAmountRequested(1000000);
//        loanApplication.setLoanApplicationNumber(10000);
//        loanApplication.setTenure(5);
//        loanApplication.setRate(-10.75);
//        int numberOfInstallment = 12;
//        double emi= addService.calculateEMI(loanApplication.getRate(),loanApplication.getLoanAmountRequested(),
//                loanApplication.getTenure(),numberOfInstallment);
//        assertNotEquals(String.valueOf(21617.95) ,String.valueOf(emi));
//    }
//    @Test
//    public void testEmiCalculationforNegativeCase3(){
//        LoanApplications loanApplication =new LoanApplications();
//        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
//        loanApplication.setLoanAmountRequested(1000000);
//        loanApplication.setLoanApplicationNumber(10000);
//        loanApplication.setTenure(5);
//        loanApplication.setRate(-10.75);
//        int numberOfInstallment = 12;
//        double emi= addService.calculateEMI(loanApplication.getRate(),loanApplication.getLoanAmountRequested(),
//                loanApplication.getTenure(),numberOfInstallment);
//        assertNotEquals(String.valueOf(21617.95) ,String.valueOf(emi));
//    }
//
//
//    @Test
//    public void testEmiCalculationforBoundaryCase(){
//        LoanApplications loanApplication =new LoanApplications();
//        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
//        loanApplication.setLoanAmountRequested(0);
//        loanApplication.setLoanApplicationNumber(10000);
//        loanApplication.setTenure(5);
//        loanApplication.setRate(10.75);
//        int numberOfInstallment = 12;
//        double emi= addService.calculateEMI(loanApplication.getRate(),loanApplication.getLoanAmountRequested(),
//                loanApplication.getTenure(),numberOfInstallment);
//        assertNotEquals(String.valueOf(21617.95) ,String.valueOf(emi));
//    }
//
//
//    @Test
//    public void testRepaymentScheduleGenerationforPositiveCase(){
//        LoanApplications loanApplication =new LoanApplications();
//        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
//        loanApplication.setLoanAmountRequested(1000000);
//        loanApplication.setLoanApplicationNumber(10000);
//        loanApplication.setTenure(5);
//        loanApplication.setRate(10.75);
//        int numberOfInstallment = 12;
//        List<RepaymentSchedule> list= addService.generateRepaymentSchedule(loanApplication,loanApplication.getRate(),
//                loanApplication.getLoanAmountRequested(),loanApplication.getTenure(),loanApplication.getInstallmentDueDate());
//        assertEquals(60,list.size());
//    }
//
//    @Test
//    public void testRepaymentScheduleGenerationforPositiveCase1(){
//        LoanApplications loanApplication =new LoanApplications();
//        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
//        loanApplication.setLoanAmountRequested(1000000);
//        loanApplication.setLoanApplicationNumber(10000);
//        loanApplication.setTenure(5);
//        loanApplication.setRate(10.75);
//        int numberOfInstallment = 12;
//        List<RepaymentSchedule> list= addService.generateRepaymentSchedule(loanApplication,loanApplication.getRate(),
//                loanApplication.getLoanAmountRequested(),loanApplication.getTenure(),loanApplication.getInstallmentDueDate());
//        assertEquals(String.valueOf(647030.47) ,String.valueOf(list.get(25).getOpeningBalance()));
//    }
//    @Test
//    public void testRepaymentScheduleGenerationforNegativeCase(){
//        LoanApplications loanApplication =new LoanApplications();
//        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
//        loanApplication.setLoanAmountRequested(1000000);
//        loanApplication.setLoanApplicationNumber(10000);
//        loanApplication.setTenure(5);
//        loanApplication.setRate(10.75);
//        int numberOfInstallment = 12;
//        List<RepaymentSchedule> list= addService.generateRepaymentSchedule(loanApplication,loanApplication.getRate(),
//                loanApplication.getLoanAmountRequested(),loanApplication.getTenure(),loanApplication.getInstallmentDueDate());
//        assertNotEquals(String.valueOf(847000.47) ,String.valueOf(list.get(15).getOpeningBalance()));
//    }
//    @Test
//    public void testRepaymentScheduleGenerationforNegativeCase1(){
//        LoanApplications loanApplication =new LoanApplications();
//        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
//        loanApplication.setLoanAmountRequested(1000000);
//        loanApplication.setLoanApplicationNumber(10000);
//        loanApplication.setTenure(5);
//        loanApplication.setRate(10.75);
//        int numberOfInstallment = 12;
//        List<RepaymentSchedule> list= addService.generateRepaymentSchedule(loanApplication,loanApplication.getRate(),
//                loanApplication.getLoanAmountRequested(),loanApplication.getTenure(),loanApplication.getInstallmentDueDate());
//        assertEquals(String.valueOf(0.0) ,String.valueOf(list.get(59).getClosingBalance()));
//    }
//    @Test
//    public void testRepaymentScheduleGenerationforBoundaryCase(){
//        LoanApplications loanApplication =new LoanApplications();
//        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
//        loanApplication.setLoanAmountRequested(1000000);
//        loanApplication.setLoanApplicationNumber(10000);
//        loanApplication.setTenure(5);
//        loanApplication.setRate(10.75);
//        int numberOfInstallment = 12;
//        List<RepaymentSchedule> list= addService.generateRepaymentSchedule(loanApplication,loanApplication.getRate(),
//                loanApplication.getLoanAmountRequested(),loanApplication.getTenure(),loanApplication.getInstallmentDueDate());
//        assertEquals(String.valueOf(60) ,String.valueOf(list.size()));
//    }
//
//}
