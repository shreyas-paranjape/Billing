package com.cybercad.billing.domain.conn.payment;
// Generated 23 Sep, 2015 4:46:26 PM by Hibernate Tools 3.2.4.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Payment generated by hbm2java
 */
@Entity
@Table(name="payment"
    ,catalog="billing"
)
public class Payment  implements java.io.Serializable {


     private Integer id;
     private Invoice invoice;
     private PaymentType paymentType;
     private Date paymentDate;
     private Double amount;
     private int tellerId;

    public Payment() {
    }

	
    public Payment(Invoice invoice, PaymentType paymentType, int tellerId) {
        this.invoice = invoice;
        this.paymentType = paymentType;
        this.tellerId = tellerId;
    }
    public Payment(Invoice invoice, PaymentType paymentType, Date paymentDate, Double amount, int tellerId) {
       this.invoice = invoice;
       this.paymentType = paymentType;
       this.paymentDate = paymentDate;
       this.amount = amount;
       this.tellerId = tellerId;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="invoice_id", nullable=false)
    public Invoice getInvoice() {
        return this.invoice;
    }
    
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="payment_type_id", nullable=false)
    public PaymentType getPaymentType() {
        return this.paymentType;
    }
    
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="payment_date", length=19)
    public Date getPaymentDate() {
        return this.paymentDate;
    }
    
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    
    @Column(name="amount", precision=22, scale=0)
    public Double getAmount() {
        return this.amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    
    @Column(name="teller_id", nullable=false)
    public int getTellerId() {
        return this.tellerId;
    }
    
    public void setTellerId(int tellerId) {
        this.tellerId = tellerId;
    }




}


