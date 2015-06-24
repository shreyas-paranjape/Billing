package com.cybercad.billing.domain.bill;

import java.util.Date;
import java.util.Set;

import com.cybercad.billing.domain.geoclass.Zone;

public class BillCycle {

	private int billCycleNumber;
	private Date startDate;
	private Date endDate;
	private Zone zone;
	private Set<Bill> bill;

}
