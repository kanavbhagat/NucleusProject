package com.nucleus.eligibiltyparameter.service;

import com.nucleus.eligibiltyparameter.model.EligibilityParameter;

import java.util.List;

public interface EligibilityParameterService {
    public List<EligibilityParameter> getAll() ;
    public String insertParameter(EligibilityParameter eligibilityParameter);
    public String insertParameterAndRequestApproval(EligibilityParameter eligibilityParameter);
    public EligibilityParameter getOneEligibilityParameter(String parameterCode);
    public String deleteEligibilityParameter(String parameterCode);
    public boolean editParameter(EligibilityParameter eligibilityParameter);
    public boolean updateStatus(String parameterCode,String newStatus,String authorizedBy);
}



