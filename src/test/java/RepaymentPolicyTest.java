//
//import com.nucleus.config.TestConfig;
//import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
//import com.nucleus.repaymentpolicy.service.RepaymentPolicyService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.time.LocalDate;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = TestConfig.class)
//public class RepaymentPolicyTest {
//
//    @Autowired
//    RepaymentPolicyService repaymentPolicyService;
//
//    @Test
//    public void getRepaymentPolicyByIdTest(){
//        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
//        repaymentPolicy.setPolicyCode("101");
//        repaymentPolicy.setPolicyName("Name1");
//        repaymentPolicy.setDate(LocalDate.now());
//        repaymentPolicy.setRepaymentFrequency("Monthly");
//        repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
//        final String id="101";
//        final RepaymentPolicy expected= repaymentPolicyService.getRepaymentPolicyById(id);
//        assertEquals(expected.toString(), repaymentPolicy.toString());
//        repaymentPolicyService.deleteRepaymentPolicy(id);
//    }
//
//    @Test
//    public void getAllRepaymentPoliciesTest(){
//        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
//        repaymentPolicy.setPolicyCode("103");
//        repaymentPolicy.setPolicyName("Name3");
//        repaymentPolicy.setDate(LocalDate.now());
//        repaymentPolicy.setRepaymentFrequency("Monthly");
//        int size1=repaymentPolicyService.getRepaymentPolicyList().size();
//        repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
//        int size2=repaymentPolicyService.getRepaymentPolicyList().size();
//        assertEquals(1,size2-size1);
//        repaymentPolicyService.deleteRepaymentPolicy("103");
//    }
//
//    @Test
//    public void addValidRepaymentPolicyTest() {
//        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
//        repaymentPolicy.setPolicyCode("102");
//        repaymentPolicy.setPolicyName("Name2");
//        repaymentPolicy.setDate(LocalDate.now());
//        repaymentPolicy.setRepaymentFrequency("Monthly");
//        boolean added=repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
//        assertTrue(added);
//        repaymentPolicyService.deleteRepaymentPolicy("102");
//    }
//
//    @Test
//    public void deleteExistingRepaymentPolicyTest(){
//        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
//        repaymentPolicy.setPolicyCode("101");
//        repaymentPolicy.setPolicyName("Name1");
//        repaymentPolicy.setDate(LocalDate.now());
//        repaymentPolicy.setRepaymentFrequency("Monthly");
//        repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
//        boolean success=repaymentPolicyService.deleteRepaymentPolicy("101");
//        assertTrue(success);
//    }
//
//
//    @Test
//    public void registerEmptyRepaymentPolicyTest(){
//        RepaymentPolicy repaymentPolicy = new RepaymentPolicy();
//        assertFalse(repaymentPolicyService.addRepaymentPolicy(repaymentPolicy));
//    }
//
//    @Test
//    public void randomRepaymentPolicySearchTest(){
//        assertNull(repaymentPolicyService.getRepaymentPolicyById("random"));
//    }
//
//    @Test
//    public void validUpdateRepaymentPolicyCheck(){
//        RepaymentPolicy originalRepaymentPolicy=new RepaymentPolicy();
//        originalRepaymentPolicy.setPolicyCode("110");
//        originalRepaymentPolicy.setPolicyName("Name10");
//        originalRepaymentPolicy.setDate(LocalDate.now());
//        originalRepaymentPolicy.setRepaymentFrequency("Monthly");
//
//        RepaymentPolicy newRepaymentPolicy=new RepaymentPolicy();
//        newRepaymentPolicy.setPolicyCode("110");
//        newRepaymentPolicy.setPolicyName("Name10");
//        newRepaymentPolicy.setDate(LocalDate.now());
//        newRepaymentPolicy.setRepaymentFrequency("Yearly");
//        repaymentPolicyService.addRepaymentPolicy(originalRepaymentPolicy);
//        assertTrue(repaymentPolicyService.updateRepaymentPolicy(newRepaymentPolicy));
//        repaymentPolicyService.deleteRepaymentPolicy("110");
//    }
//
//    @Test
//    public void updateRepaymentPolicyNameWithExistingNameCheck(){
//        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
//        repaymentPolicy.setPolicyCode("111");
//        repaymentPolicy.setPolicyName("Name111");
//        repaymentPolicy.setDate(LocalDate.now());
//        repaymentPolicy.setRepaymentFrequency("Monthly");
//
//        repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
//
//        RepaymentPolicy originalRepaymentPolicy=new RepaymentPolicy();
//        originalRepaymentPolicy.setPolicyCode("110");
//        originalRepaymentPolicy.setPolicyName("Name10");
//        originalRepaymentPolicy.setDate(LocalDate.now());
//        originalRepaymentPolicy.setRepaymentFrequency("Monthly");
//
//        RepaymentPolicy newRepaymentPolicy=new RepaymentPolicy();
//        newRepaymentPolicy.setPolicyCode("110");
//        newRepaymentPolicy.setPolicyName("Name111");
//        newRepaymentPolicy.setDate(LocalDate.now());
//        newRepaymentPolicy.setRepaymentFrequency("Yearly");
//        repaymentPolicyService.addRepaymentPolicy(originalRepaymentPolicy);
//        assertFalse(repaymentPolicyService.updateRepaymentPolicy(newRepaymentPolicy));
//        repaymentPolicyService.deleteRepaymentPolicy("110");
//        repaymentPolicyService.deleteRepaymentPolicy("111");
//    }
//
//    @Test
//    public void updateStatusCheck(){
//        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
//        repaymentPolicy.setPolicyCode("110");
//        repaymentPolicy.setPolicyName("Name10");
//        repaymentPolicy.setDate(LocalDate.now());
//        repaymentPolicy.setRepaymentFrequency("Monthly");
//        repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
//        String status="Pending";
//        boolean success=repaymentPolicyService.changeStatus("110",status);
//        assertTrue(success);
//        repaymentPolicyService.deleteRepaymentPolicy("110");
//    }
//
//    @Test
//    public void updateCreationParametersTest(){
//        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
//        repaymentPolicy.setPolicyCode("110");
//        repaymentPolicy.setPolicyName("Name10");
//        repaymentPolicy.setDate(LocalDate.now());
//        repaymentPolicy.setRepaymentFrequency("Monthly");
//        repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
//        String name="Creator";
//        repaymentPolicyService.updateCreationParameters("110",name);
//        boolean success=repaymentPolicyService.updateCreationParameters("110",name);
//        assertTrue(success);
//        repaymentPolicyService.deleteRepaymentPolicy("110");
//    }
//
//    @Test
//    public void updateModificationParametersTest(){
//        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
//        repaymentPolicy.setPolicyCode("110");
//        repaymentPolicy.setPolicyName("Name10");
//        repaymentPolicy.setDate(LocalDate.now());
//        repaymentPolicy.setRepaymentFrequency("Monthly");
//        repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
//        String name="Modifier";
//        repaymentPolicyService.updateModificationParameters("110",name);
//        boolean success=repaymentPolicyService.updateModificationParameters("110",name);
//        assertTrue(success);
//        repaymentPolicyService.deleteRepaymentPolicy("110");
//    }
//
//    @Test
//    public void updateAuthorizationParametersTest(){
//
//        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
//        repaymentPolicy.setPolicyCode("110");
//        repaymentPolicy.setPolicyName("Name10");
//        repaymentPolicy.setDate(LocalDate.now());
//        repaymentPolicy.setRepaymentFrequency("Monthly");
//        repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
//        String name="Authorizer";
//        boolean success=repaymentPolicyService.updateAuthorizationParameters("110",name);
//        assertTrue(success);
//        repaymentPolicyService.deleteRepaymentPolicy("110");
//    }
//}
