package com.nucleus.eligibiltyparameter.service;


import com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO;
import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EligibilityParameterServiceImpl implements EligibilityParameterService{
    @Autowired
    private EligibilityParameterDAO eligibilityParameterDao;
    @Override
    public List<EligibilityParameter> getAll(){
        return eligibilityParameterDao.getAll();
    }
    @Override
    public String insertParameter(EligibilityParameter eligibilityParameter){
        return eligibilityParameterDao.insertParameter(eligibilityParameter);
    }
    @Override
    public String insertParameterAndRequestApproval(EligibilityParameter eligibilityParameter){
        return eligibilityParameterDao.insertParameterAndRequestApproval(eligibilityParameter);
    }

    @Override
    public EligibilityParameter getOneEligibilityParameter(String parameterCode){
        return eligibilityParameterDao.getOneEligibilityParameter(parameterCode);
    }

    @Override
    public String deleteEligibilityParameter(String parameterCode){
        return eligibilityParameterDao.deleteEligibilityParameter(parameterCode);
    }

    @Override
    public boolean editParameter(EligibilityParameter eligibilityParameter){
        return eligibilityParameterDao.editParameter(eligibilityParameter);
    }

    @Override
    public boolean updateStatus(String parameterCode,String newStatus,String authorizedBy)
    {
        return eligibilityParameterDao.updateStatus(parameterCode,newStatus,authorizedBy);
    }
}