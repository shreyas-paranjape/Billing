package com.cybercad.billing.domain.conn;

import java.util.Date;

public class ConnectionStatus {

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
}
