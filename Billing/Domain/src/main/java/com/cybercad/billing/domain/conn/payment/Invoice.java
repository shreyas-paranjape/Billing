package com.cybercad.billing.domain.conn.payment;
// Generated 23 Sep, 2015 4:46:26 PM by Hibernate Tools 3.2.4.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cybercad.billing.domain.conn.reading.ConnReading;

/**
 * Invoice generated by hbm2java
 */
@Entity
@Table(name="invoice"
    ,catalog="billing"
)
public class Invoice  implements java.io.Serializable {


     private Integer id;
     private Double waterCharge;
     private Double sundry;
     private Double sewageCharge;
     private Double meterRentCharge;
     private Date issueDate;
     private Set<Payment> payments = new HashSet<Payment>(0);
     private Set<ConnReading> connReadings = new HashSet<ConnReading>(0);

    public Invoice() {
    }

    public Invoice(Double waterCharge, Double sundry, Double sewageCharge, Double meterRentCharge, Date issueDate, Set<Payment> payments, Set<ConnReading> connReadings) {
       this.waterCharge = waterCharge;
       this.sundry = sundry;
       this.sewageCharge = sewageCharge;
       this.meterRentCharge = meterRentCharge;
       this.issueDate = issueDate;
       this.payments = payments;
       this.connReadings = connReadings;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="water_charge", precision=22, scale=0)
    public Double getWaterCharge() {
        return this.waterCharge;
    }
    
    public void setWaterCharge(Double waterCharge) {
        this.waterCharge = waterCharge;
    }

    
    @Column(name="sundry", precision=22, scale=0)
    public Double getSundry() {
        return this.sundry;
    }
    
    public void setSundry(Double sundry) {
        this.sundry = sundry;
    }

    
    @Column(name="sewage_charge", precision=22, scale=0)
    public Double getSewageCharge() {
        return this.sewageCharge;
    }
    
    public void setSewageCharge(Double sewageCharge) {
        this.sewageCharge = sewageCharge;
    }

    
    @Column(name="meter_rent_charge", precision=22, scale=0)
    public Double getMeterRentCharge() {
        return this.meterRentCharge;
    }
    
    public void setMeterRentCharge(Double meterRentCharge) {
        this.meterRentCharge = meterRentCharge;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="issue_date", length=19)
    public Date getIssueDate() {
        return this.issueDate;
    }
    
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="invoice")
    public Set<Payment> getPayments() {
        return this.payments;
    }
    
    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="invoice")
    public Set<ConnReading> getConnReadings() {
        return this.connReadings;
    }
    
    public void setConnReadings(Set<ConnReading> connReadings) {
        this.connReadings = connReadings;
    }




}


