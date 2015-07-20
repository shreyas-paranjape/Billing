package com.cybercad.billing.domain.bill;

import java.util.Calendar;
import java.util.Date;

import com.cybercad.billing.domain.conn.payment.ConnectionPayment;
import com.cybercad.billing.domain.conn.reading.ConnectionReading;

public class Bill {

	public static int BILL_PAYMENT_PERIOD = 14;

	// Unique for a zone
	private int billNumber;

	private ConnectionReading reading;
	private ConnectionPayment payment;

	private double waterCharge;
	private double sundryCharge;
	private double meterRent;

	private Date issueDate;
	private Date fromDate;
	private Date toDate;

	public Date getDueDate() {
		Calendar dueDate = Calendar.getInstance();
		dueDate.setTime(issueDate);
		dueDate.add(Calendar.DAY_OF_YEAR, BILL_PAYMENT_PERIOD);
		return dueDate.getTime();
	}

	public double getTotalCharge() {
		return waterCharge + sundryCharge + meterRent;
	}

}
