package com.nucleus.receipt.model;

import javax.persistence.*;

@Entity
@Table( name= "settlement")
public class Settlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id",updatable = false, nullable = false)
    private long paymentId;

    @Column(name="amount_due", nullable = false)
    private int amountDue;

    @Column(name="amount_paid", nullable = false)
    private int amountPaid;

    @Column(name="status", nullable = false)
    private String status;

    //@Column(name="advice_id",length = 10, nullable = false)
    @ManyToOne
    @JoinColumn(name="advice_id",referencedColumnName ="advice_id" ,nullable = false)
    private Advice adviceId;

    //@Column(name="receipt_no",length = 10, nullable = false)
    @ManyToOne
    @JoinColumn(name="receipt_no",referencedColumnName ="receipt_no" ,nullable = false)
    private Receipt receiptNo;


    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public int getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(int amountDue) {
        this.amountDue = amountDue;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Advice getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(Advice adviceId) {
        this.adviceId = adviceId;
    }

    public Receipt getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(Receipt receiptNo) {
        this.receiptNo = receiptNo;
    }
}
