package com.nucleus.receipt.dao;

import com.nucleus.receipt.model.Advice;
import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.model.Settlement;
import java.util.List;

public interface ReceiptDAOInterface {

    Boolean createNewReceipt(Receipt receipt);
    List<Receipt> receiptSearch(String receiptType, String receiptBasis, Integer accountNumber, Integer receiptNo);
    List<Receipt> getReceiptList();
    public Boolean updateReceipt(Receipt receipt);
    public Receipt getReceipt(Integer receiptId);
    public Boolean runBOD(Receipt receipt, Advice advice, Settlement settlement);
}
