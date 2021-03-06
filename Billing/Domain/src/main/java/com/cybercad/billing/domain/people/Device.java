package com.cybercad.billing.domain.people;
// Generated 23 Sep, 2015 4:46:26 PM by Hibernate Tools 3.2.4.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Device generated by hbm2java
 */
@Entity
@Table(name="device"
    ,catalog="billing"
)
public class Device  implements java.io.Serializable {


     private Integer id;
     private MeterReader meterReader;
     private int userId;
     private String type;
     private String code;

    public Device() {
    }

	
    public Device(MeterReader meterReader, int userId) {
        this.meterReader = meterReader;
        this.userId = userId;
    }
    public Device(MeterReader meterReader, int userId, String type, String code) {
       this.meterReader = meterReader;
       this.userId = userId;
       this.type = type;
       this.code = code;
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
    @JoinColumn(name="meter_reader_id", nullable=false)
    public MeterReader getMeterReader() {
        return this.meterReader;
    }
    
    public void setMeterReader(MeterReader meterReader) {
        this.meterReader = meterReader;
    }

    
    @Column(name="User_id", nullable=false)
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    @Column(name="type", length=2)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    
    @Column(name="code", length=45)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }




}


