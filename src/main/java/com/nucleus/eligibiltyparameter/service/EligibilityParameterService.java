package com.nucleus.eligibiltyparameter.service;

import com.nucleus.eligibiltyparameter.model.EligibilityParameter;

import java.util.List;

public interface EligibilityParameterService {
    public List<EligibilityParameter> getAll() ;
    public void insertParameter(EligibilityParameter eligibilityParameter);
    public void insertParameterAndRequestApproval(EligibilityParameter eligibilityParameter);
}


