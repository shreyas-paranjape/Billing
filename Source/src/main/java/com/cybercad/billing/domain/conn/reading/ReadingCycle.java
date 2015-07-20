package com.cybercad.billing.domain.conn.reading;

import java.util.Date;
import java.util.Set;

import com.cybercad.billing.domain.people.MeterReader;

public class ReadingCycle {

	private int number;
	private Date startDate;
	private Date endDate;
	private Set<ConnectionReading> readings;
	private MeterReader meterReader;

}
