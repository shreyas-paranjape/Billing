package com.cybercad.billing.domain.conn;

import com.cybercad.billing.domain.devices.Meter;
import com.cybercad.billing.domain.geoclass.Ward;
import com.cybercad.billing.domain.people.Consumer;

public class Connection {

	private String code;
	private Consumer consumer;
	private int minUnits;
	private Meter activeMeter;
	private Ward ward;

}
