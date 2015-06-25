package com.cybercad.billing.domain.bill;

import java.util.Calendar;
import java.util.Date;

import com.cybercad.billing.domain.conn.Connection;

public class Bill extends Reading {

	public static int BILL_PAYMENT_PERIOD = 14;

	private Connection connection;
	private BillingCycle billingCycle;

	private double WaterCharge;
	private double sundryCharge;
	private Date issueDate;
	private Date fromDate;
	private Date toDate;
	// Unique for a zone
	private int billNumber;

	public Date getDueDate() {
		Calendar dueDate = Calendar.getInstance();
		dueDate.setTime(issueDate);
		dueDate.add(Calendar.DAY_OF_YEAR, BILL_PAYMENT_PERIOD);
		return dueDate.getTime();
	}
	
}
