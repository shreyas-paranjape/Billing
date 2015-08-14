package com.cybercad.billing.domain.conn.reading;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cybercad.billing.domain.people.MeterReader;
@Entity
@Table(name="read_cycle")
public class ReadingCycle {
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
//	private int number;
	private Date startDate;
	private Date endDate;
//	private Set<ConnectionReading> readings;
	@ManyToOne
	private MeterReader meterReader;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public MeterReader getMeterReader() {
		return meterReader;
	}
	public void setMeterReader(MeterReader meterReader) {
		this.meterReader = meterReader;
	}

}
