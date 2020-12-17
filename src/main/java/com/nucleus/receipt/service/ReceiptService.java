package com.nucleus.receipt.service;

import com.nucleus.receipt.dao.ReceiptDAOInterface;
import com.nucleus.receipt.model.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    @Autowired
    ReceiptDAOInterface receiptDAOInterface;

    public boolean registerReceipt(Receipt receipt){
        return receiptDAOInterface.createNewReceipt(receipt);
    }
}
