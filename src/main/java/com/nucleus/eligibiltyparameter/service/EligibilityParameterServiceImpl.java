package com.nucleus.eligibiltyparameter.service;


import com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO;
import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class
EligibilityParameterServiceImpl implements EligibilityParameterService{
    @Autowired
    private EligibilityParameterDAO eligibilityParameterDao;

    /**
     * Getting list of all eligibility parameters
     * @return - returns a list of all eligibility parameters
     */
    @Override
    public List<EligibilityParameter> getAll(){
        return eligibilityParameterDao.getAll();
    }

    /**
     * Inserting eligibility parameters into database
     * @param eligibilityParameter - object of Eligibility Parameter to be inserted
     * @return parameterCode against which parameter is inserted
     */
    @Override
    public boolean insertParameter(EligibilityParameter eligibilityParameter){
        return eligibilityParameterDao.insertParameter(eligibilityParameter);
    }

    /**
     * Getting a particular eligibility parameter
     * @param parameterCode - get eligibility parameter having a particular parameter code
     * @return object of eligibility parameter
     */
    @Override
    public EligibilityParameter getOneEligibilityParameter(String parameterCode){
        return eligibilityParameterDao.getOneEligibilityParameter(parameterCode);
    }

    /**
     * Delete a particular eligibility parameter
     * @param parameterCode - Delete eligibility parameter against parameter code
     * @return parameter code against which eligibility parameter is deleted
     */
    @Override
    public boolean deleteEligibilityParameter(String parameterCode){
        return eligibilityParameterDao.deleteEligibilityParameter(parameterCode);
    }

    /**
     * Edit eligibility parameter
     * @param eligibilityParameter - object of eligibility parameter to be edited
     * @return - true if successfully edited else false
     */
    @Override
    public boolean editParameter(EligibilityParameter eligibilityParameter){
        return eligibilityParameterDao.editParameter(eligibilityParameter);
    }

    /**
     * Update status of eligibility parameter
     * @param parameterCode - updating status of particular eligibility parameter identified by parameter code
     * @param newStatus - new status to be updated
     * @param authorizedBy - name of the person who has updated eligibility parameter
     * @return true if updated successfully, else false
     */
    @Override
    public boolean updateStatus(String parameterCode,String newStatus,String authorizedBy)
    {
        return eligibilityParameterDao.updateStatus(parameterCode,newStatus,authorizedBy);
    }

    /**
     * Fetching list of all eligibility parameters whose status is approved
     * @return - list of all eligibility parameters whose status is approved
     */
    @Override
    public List<EligibilityParameter> getApprovedParameters(){
        return eligibilityParameterDao.getApprovedParameters();
    }
}