package com.cybercad.billing.domain.conn.reading;

import java.util.Date;

public class ConnectionReading {

	private Fault fault;
	private long units;
	private Date readingDate;

	public boolean isFault() {
		return null != fault;
	}

	public long getUnits() {
		return units;
	}

	public void setUnits(long units) {
		this.units = units;
	}

}
