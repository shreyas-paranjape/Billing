package com.cybercad.billing.domain;
// Generated 13 Aug, 2015 2:58:27 AM by Hibernate Tools 3.2.4.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ReadCycle generated by hbm2java
 */
@Entity
@Table(name="read_cycle"
    ,catalog="billing"
)
public class ReadCycle  implements java.io.Serializable {


     private Integer id;
     private MeterReader meterReader;
     private Date startDate;
     private Date endDate;
     private Set<ConnReading> connReadings = new HashSet<ConnReading>(0);

    public ReadCycle() {
    }

	
    public ReadCycle(MeterReader meterReader) {
        this.meterReader = meterReader;
    }
    public ReadCycle(MeterReader meterReader, Date startDate, Date endDate, Set<ConnReading> connReadings) {
       this.meterReader = meterReader;
       this.startDate = startDate;
       this.endDate = endDate;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="meter_reader_id", nullable=false)
    public MeterReader getMeterReader() {
        return this.meterReader;
    }
    
    public void setMeterReader(MeterReader meterReader) {
        this.meterReader = meterReader;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="start_date", length=10)
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="end_date", length=10)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="readCycle")
    public Set<ConnReading> getConnReadings() {
        return this.connReadings;
    }
    
    public void setConnReadings(Set<ConnReading> connReadings) {
        this.connReadings = connReadings;
    }




}


