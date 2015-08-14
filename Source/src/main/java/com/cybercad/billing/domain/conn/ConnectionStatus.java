package com.cybercad.billing.domain.conn;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="conn_status")
public class ConnectionStatus {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private double currentBalance;			
	private double currentReading;
	private long currentAverage;
	private Date prevReadingDate;

	public double getCurrentReading() {
		return currentReading;
	}

	public void setCurrentReading(double currentReading) {
		this.currentReading = currentReading;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public long getCurrentAverage() {
		return currentAverage;
	}

	public void setCurrentAverage(long currentAverage) {
		this.currentAverage = currentAverage;
	}

	public Date getPrevReadingDate() {
		return prevReadingDate;
	}

	public void setPrevReadingDate(Date prevReadingDate) {
		this.prevReadingDate = prevReadingDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
