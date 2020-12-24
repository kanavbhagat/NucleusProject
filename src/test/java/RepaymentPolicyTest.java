
import com.nucleus.config.TestConfig;
import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import com.nucleus.repaymentpolicy.service.RepaymentPolicyServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class RepaymentPolicyTest {

    @Autowired
    RepaymentPolicyServiceImpl repaymentPolicyService;

    @Test
    public void getRepaymentPolicyByIdTest(){

        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
        repaymentPolicy.setPolicyCode("101");
        repaymentPolicy.setPolicyName("Name1");
        repaymentPolicy.setDate(LocalDate.now());
        repaymentPolicy.setRepaymentFrequency("Monthly");

        repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);

        final String id="101";

        final RepaymentPolicy expected= repaymentPolicyService.getRepaymentPolicyById(id);
        assertEquals(expected.toString(), repaymentPolicy.toString());

        repaymentPolicyService.deleteRepaymentPolicy(id);
    }

    @Test
    public void getAllRepaymentPoliciesTest(){

        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
        repaymentPolicy.setPolicyCode("103");
        repaymentPolicy.setPolicyName("Name3");
        repaymentPolicy.setDate(LocalDate.now());
        repaymentPolicy.setRepaymentFrequency("Monthly");
        repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);

        assertFalse(repaymentPolicyService.getRepaymentPolicyList().isEmpty());

        repaymentPolicyService.deleteRepaymentPolicy("103");
    }

    @Test
    public void addValidRepaymentPolicyTest()
    {
        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
        repaymentPolicy.setPolicyCode("102");
        repaymentPolicy.setPolicyName("Name2");
        repaymentPolicy.setDate(LocalDate.now());
        repaymentPolicy.setRepaymentFrequency("Monthly");

        boolean added=repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
        assertNotNull(repaymentPolicyService.getRepaymentPolicyById("102"));

        repaymentPolicyService.deleteRepaymentPolicy("102");
    }

    @Test
    public void deleteExistingRepaymentPolicyTest(){

        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
        repaymentPolicy.setPolicyCode("101");
        repaymentPolicy.setPolicyName("Name1");
        repaymentPolicy.setDate(LocalDate.now());
        repaymentPolicy.setRepaymentFrequency("Monthly");

        repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);

        assertNotNull(repaymentPolicyService.getRepaymentPolicyById("101"));

        repaymentPolicyService.deleteRepaymentPolicy("101");

//        Now already deleted
        assertNull(repaymentPolicyService.getRepaymentPolicyById("101"));
    }


    @Test
    public void registerEmptyRepaymentPolicyTest(){
        RepaymentPolicy repaymentPolicy = new RepaymentPolicy();
        assertFalse(repaymentPolicyService.addRepaymentPolicy(repaymentPolicy));
    }

    @Test
    public void randomRepaymentPolicySearchTest(){
        assertNull(repaymentPolicyService.getRepaymentPolicyById("random"));
    }

    @Test
    public void updateStatusCheck(){

        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
        repaymentPolicy.setPolicyCode("110");
        repaymentPolicy.setPolicyName("Name10");
        repaymentPolicy.setDate(LocalDate.now());
        repaymentPolicy.setRepaymentFrequency("Monthly");

        repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);

        String status="Pending";
        repaymentPolicyService.changeStatus("110",status);

        assertEquals(status, repaymentPolicyService.getRepaymentPolicyById("110").getStatus());

        repaymentPolicyService.deleteRepaymentPolicy("110");

    }

    @Test
    public void updateCreationModificationAuthorizationParametersTest(){

        RepaymentPolicy repaymentPolicy=new RepaymentPolicy();
        repaymentPolicy.setPolicyCode("110");
        repaymentPolicy.setPolicyName("Name10");
        repaymentPolicy.setDate(LocalDate.now());
        repaymentPolicy.setRepaymentFrequency("Monthly");


        repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);

        String name1="Creator";
        String name2="Modifier";
        String name3="Authorizer";


        repaymentPolicyService.updateCreationParameters("110",name1);
        repaymentPolicyService.updateModificationParameters("110",name2);
        repaymentPolicyService.updateAuthorizationParameters("110",name3);

        assertEquals(name1, repaymentPolicyService.getRepaymentPolicyById("110").getCreatedBy());
        assertEquals(name2, repaymentPolicyService.getRepaymentPolicyById("110").getModifiedBy());
        assertEquals(name3, repaymentPolicyService.getRepaymentPolicyById("110").getAuthorizedBy());

        repaymentPolicyService.deleteRepaymentPolicy("110");

    }
}
