package com.nucleus.receipt.service;

import com.nucleus.receipt.dao.ReceiptDAOInterface;
import com.nucleus.receipt.model.Advice;
import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.model.Settlement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p> Service calling the dao handling all data operations for receipts. </p>
 */
@Service
public class ReceiptService {

    @Autowired
    ReceiptDAOInterface receiptDAOInterface;

    /**
     * <p> creates a new receipt in the database. returns true if successful, else false. </p>
     * @param Receipt receipt the receipt object to be saved
     * @return true if operation was successful, or false.
     */
    public boolean registerReceipt(Receipt receipt){
        return receiptDAOInterface.createNewReceipt(receipt);
    }


    /**
     * <p> Searches for a receipt in the database based on four parameters </p>
     * @param String receiptType of the receipt object to be found
     * @param String receiptBasis of the receipt object to be found
     * @param Integer accountNumber of the receipt object to be found
     * @param Integer receiptNo of the receipt object to be found
     * @return List with matching receipt objects, else an empty list.
     */
    public List<Receipt> receiptSearch(String receiptType, String receiptBasis, Integer accountNumber, Integer receiptNo){
        return receiptDAOInterface.receiptSearch(receiptType, receiptBasis, accountNumber, receiptNo);
    }


    /**
     * <p> retrieves a list of all the receipts from the database. returns a list of receipts. </p>
     * @return list of all receipts, or empty list if no receipts found.
     */
    public List<Receipt>getReceiptList(){
        return receiptDAOInterface.getReceiptList();
    }


    /**
     * <p> updates a receipt in the database. returns true if successful, else false. </p>
     * @param Receipt receipt the receipt object to be updated
     * @return true if operation was successful, or false.
     */
    public Boolean updateReceipt(Receipt receipt){
        return receiptDAOInterface.updateReceipt(receipt);
    }


    /**
     * <p> retrieves a receipt by id from the database. returns the receipt if succssful, else null. </p>
     * @param String receiptId receipt Id of the receipt to be retrieved
     * @return retrieved receipt object if successful, else null.
     */
    public Receipt getReceipt(Integer receiptId){
        return receiptDAOInterface.getReceipt(receiptId);
    }


    /**
     * <p> Runs the BOD process by updating the receipt object, and adding the advice and settlement objects. </p>
     * @param Receipt receipt receipt object to be udpated
     * @param Advice advice advice object to be created.
     * @param Settlement settlement settlement object to be created.
     * @return retrieved true if all operations were successful, else false.
     */
    public Boolean runBOD(Receipt receipt, Advice advice, Settlement settlement){
        return receiptDAOInterface.runBOD(receipt, advice, settlement);
    }
}
