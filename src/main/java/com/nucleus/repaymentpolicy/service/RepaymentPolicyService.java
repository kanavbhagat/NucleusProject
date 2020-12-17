package com.nucleus.repaymentpolicy.service;

import com.nucleus.repaymentpolicy.dao.RepaymentPolicyDaoImpl;
import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepaymentPolicyService {

    @Autowired
    RepaymentPolicyDaoImpl repaymentPolicyDao;

    public void addTempRepaymentPolicy()
    {
        RepaymentPolicy repaymentPolicy = new RepaymentPolicy();
        repaymentPolicy.setPolicyCode("RM101");
        repaymentPolicy.setPolicyName("Policy1");
        repaymentPolicy.setPolicyDescription("desc");
        repaymentPolicy.setCreatedBy("x1");
        repaymentPolicy.setAuthorizedBy("y");
        repaymentPolicy.setModifiedBy("z");
        boolean b = repaymentPolicyDao.addRepaymentPolicy(repaymentPolicy);
        RepaymentPolicy repaymentPolicy1 = new RepaymentPolicy();
        repaymentPolicy1.setPolicyCode("RM102");
        repaymentPolicy1.setPolicyName("Policy2");
        repaymentPolicy1.setPolicyDescription("desc");
        repaymentPolicy1.setCreatedBy("x1");
        repaymentPolicy1.setAuthorizedBy("y1");
        repaymentPolicy1.setModifiedBy("z1");
        b = repaymentPolicyDao.addRepaymentPolicy(repaymentPolicy1);
        RepaymentPolicy repaymentPolicy2 = new RepaymentPolicy();
        repaymentPolicy2.setPolicyCode("RM103");
        repaymentPolicy2.setPolicyName("Policy0");
        repaymentPolicy2.setPolicyDescription("desc3");
        repaymentPolicy2.setCreatedBy("x153");
        repaymentPolicy2.setAuthorizedBy("y143");
        repaymentPolicy2.setModifiedBy("z143");
        b = repaymentPolicyDao.addRepaymentPolicy(repaymentPolicy2);
        System.out.println("added");
    }

    public List<RepaymentPolicy> getRepaymentPolicyList()
    {
        List<RepaymentPolicy> policyList= repaymentPolicyDao.getRepaymentPolicyList();
        return policyList;
    }
}
