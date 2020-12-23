package com.nucleus.receipt.aspect;

import com.nucleus.receipt.model.Receipt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class ReceiptAspect {


   Logger logger = LoggerFactory.getLogger(ReceiptAspect.class);
    @Pointcut("execution (* com.nucleus.receipt.service.ReceiptService.registerReceipt(..))")
    public void register(){}

    @Pointcut("execution (* com.nucleus.receipt.service.ReceiptService.updateReceipt(..))")
    public void update(){}

    @Pointcut("execution (* com.nucleus.receipt.service.ReceiptService.receiptSearch(..))")
    public void search(){}

    @Pointcut("execution (* com.nucleus.receipt.service.ReceiptService.getReceiptList(..))")
    public void receiptList(){}

    @Pointcut("execution (* com.nucleus.receipt.service.ReceiptService.getReceipt(..))")
    public void receiptById(){}

    @Pointcut("execution (* com.nucleus.receipt.service.ReceiptService.runBOD(..))")
    public void BOD(){}

    @AfterReturning(pointcut = "register()",returning = "isCreated")
    public void registerReceipt(JoinPoint joinPoint,boolean isCreated){
        Object[] args = joinPoint.getArgs();
        if(isCreated) {
            Receipt receipt = (Receipt) args[0];
            logger.info("New receipt registered successfully with Receipt No. " + receipt.getReceiptNo());
        }else{
            logger.info("New receipt not created. Method returned "+ isCreated);
        }
    }

    @AfterReturning(pointcut = "update()",returning = "isUpdated")
    public void updateReceipt(JoinPoint joinPoint,boolean isUpdated){
        Object[] args = joinPoint.getArgs();
        if(isUpdated) {
            Receipt receipt = (Receipt) args[0];
            logger.info("Receipt updated with status " + receipt.getReceiptStatus());
        }else {
            logger.info("Receipt not updated. ReceiptDAO.updateReceipt() returned " + isUpdated);
        }
    }

    @AfterReturning(pointcut = "search()",returning = "searchedList")
    public void searchReceipt(JoinPoint joinPoint,List<Object>searchedList){
        if(searchedList!=null && !searchedList.isEmpty()){
            logger.info("Searched Receipt list fetched from database with size "+searchedList.size());
        }else {
            logger.info("Searched Receipt list fetched, but returned empty or null");
        }

    }

    @AfterReturning(pointcut = "receiptList()",returning = "receiptList")
    public void getReceiptList(JoinPoint joinPoint, List<Receipt>receiptList){
        if( receiptList!=null && !receiptList.isEmpty()) {
            logger.info("Receipt list fetched from database with size "+receiptList.size());
        } else {
            logger.info("Receipt list fetched, but returned empty or null.");
        }
    }

    @AfterReturning(pointcut = "receiptById()",returning = "receipt")
    public void getReceiptById(JoinPoint joinPoint,Object receipt){
        Object[] args = joinPoint.getArgs();
        if(receipt!=null) {
            Integer receiptId = (Integer) args[0];
            logger.info("Receipt fetched from Database by Id "+receiptId);
        }else {
            logger.info("Receipt not fetched by id. ReceiptDAO.getReceipt() returned null.");
        }
    }

    @AfterReturning(pointcut = "BOD()",returning = "bod")
    public void runBOD(JoinPoint joinPoint,boolean bod){
        if(bod) {
            logger.info("Receipt BOD process ran successfully");
        }else{
            logger.info("Receipt BOD process not ran successfully. Method returned " + bod);
        }
    }


}
