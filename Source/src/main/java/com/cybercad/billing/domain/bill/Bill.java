package com.cybercad.billing.domain.bill;

import java.util.Date;

import com.cybercad.billing.domain.conn.Connection;

public class Bill {

	private Connection connection;
	private BillCycle biilCycle;
	private double WaterCharge;
	private double sundryCharge;
	private Date issueDate;
	private Date fromDate;
	private Date toDate;
	// Unique for a zone
	private int billNumber;
}
