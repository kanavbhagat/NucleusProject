package com.nucleus.eligibiltyparameter.database;

import com.nucleus.eligibiltyparameter.model.EligibilityParameter;

import java.util.List;

public interface EligibilityParameterDAO {
    public List<EligibilityParameter> getAll() ;
    public void insertParameter(EligibilityParameter eligibilityParameter);
    public void insertParameterAndRequestApproval(EligibilityParameter eligibilityParameter);
}
