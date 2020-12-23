package com.nucleus.eligibiltyparameter.database;

import com.nucleus.eligibiltyparameter.model.EligibilityParameter;

import java.util.List;

public interface EligibilityParameterDAO {
    public List<EligibilityParameter> getAll() ;
    public String insertParameter(EligibilityParameter eligibilityParameter);
    public EligibilityParameter getOneEligibilityParameter(String parameterCode);
    public String deleteEligibilityParameter(String parameterCode);
    public boolean editParameter(EligibilityParameter eligibilityParameter);
    public boolean updateStatus(String parameterCode,String newStatus,String authorizedBy);
}