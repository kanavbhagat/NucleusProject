package com.nucleus.receipt.aspect;



import com.nucleus.receipt.model.Receipt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;




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

    @AfterReturning("register()")
    public void registerReceipt(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Receipt receipt = (Receipt) args[0];
        logger.info("New receipt registered successfully with Receipt No. "+receipt.getReceiptNo());
    }

    @AfterReturning("update()")
    public void updateReceipt(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Receipt receipt = (Receipt)args[0];
        logger.info("Receipt updated with status "+receipt.getReceiptStatus());
    }

    @AfterReturning("search()")
    public void searchReceipt(JoinPoint joinPoint){
        logger.info("Search method invoked");
    }

    @AfterReturning("receiptList()")
    public void getReceiptList(JoinPoint joinPoint){
        logger.info("Receipt list fetched from Database");
    }

    @AfterReturning("receiptById()")
    public void getReceiptById(JoinPoint joinPoint){
        logger.info("Receipt fetched from Database by Id");
    }


}
