package com.nucleus.receipt.service;

import com.nucleus.receipt.dao.ReceiptDAOInterface;
import com.nucleus.receipt.model.Advice;
import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.model.Settlement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    ReceiptDAOInterface receiptDAOInterface;

    public boolean registerReceipt(Receipt receipt){
        return receiptDAOInterface.createNewReceipt(receipt);
    }

    public List<Object> receiptSearch(String receiptType, String receiptBasis, Integer accountNumber, Integer receiptNo){
        return receiptDAOInterface.receiptSearch(receiptType, receiptBasis, accountNumber, receiptNo);
    }

    public List<Receipt>getReceiptList(){
        return receiptDAOInterface.getReceiptList();
    }

    public Boolean updateReceipt(Receipt receipt){
        return receiptDAOInterface.updateReceipt(receipt);
    }

    public Receipt getReceipt(Integer receiptId){
        return receiptDAOInterface.getReceipt(receiptId);
    }

    public Boolean runBOD(Receipt receipt, Advice advice, Settlement settlement){
        return receiptDAOInterface.runBOD(receipt, advice, settlement);
    }
}
