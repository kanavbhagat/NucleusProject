package com.nucleus.receipt.dao;

import com.nucleus.receipt.model.Receipt;

import java.util.List;

public interface ReceiptDAOInterface {

    Boolean createNewReceipt(Receipt receipt);
    List<Receipt> getReceiptList(String rtype, String rBasis, String accountNo, String rRef);
}
