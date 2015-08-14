package com.cybercad.billing.domain.conn.reading;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.cybercad.billing.domain.conn.Connection;
import com.cybercad.billing.domain.tariff.TariffClass;
//@Entity
//@Table(name="conn_reading")
public class ConnectionReading {
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	//@ManyToOne
	private Connection conn;
	private Date readingDate;
	private long units;
	private long charges;
	@ManyToOne
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
			return conn.calculateMinUnits();
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Date getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(Date readingDate) {
		this.readingDate = readingDate;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}

	public void setCharges(long charges) {
		this.charges = charges;
	}

}
