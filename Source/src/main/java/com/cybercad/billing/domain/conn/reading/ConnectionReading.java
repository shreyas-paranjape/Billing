package com.cybercad.billing.domain.conn.reading;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.cybercad.billing.domain.conn.Connection;
import com.cybercad.billing.domain.tariff.TariffClass;

public class ConnectionReading {

	private Connection conn;
	private Date readingDate;
	private long units;
	private long charges;
	private Fault fault;

	public long getPeriod() {
		Date prevReadingDate = conn.getConnectionStatus().getPrevReadingDate();
		DateTime prevDate = new DateTime(prevReadingDate);
		DateTime curDate = new DateTime(readingDate);
		return Days.daysBetween(prevDate.toLocalDate(), curDate.toLocalDate())
				.getDays();
	}

	public boolean isFault() {
		return null != fault;
	}

	public long getUnits() {
		if (isFault()) {
			return conn.getMinUnits();
		}
		return getUnits();
	}

	public void setUnits(long units) {
		this.units = units;
	}

	public long getCharges() {
		final long period = getPeriod();
		final TariffClass tariffClass = conn.getConnectionType()
				.getTariffClass();
		final double chargesPerDay = tariffClass
				.getChargesPerDay(getUnitsPerDay(period));
		charges = Math.round(chargesPerDay * period);
		return charges;
	}

	public float getUnitsPerDay(long period) {
		return units / period;
	}

}
