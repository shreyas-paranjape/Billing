package com.cybercad.billing.domain.geoclass;

import java.util.Set;

import com.cybercad.billing.domain.conn.Connection;
import com.cybercad.billing.domain.people.MeterReader;

public class Ward {

	private SubDivision division;
	private Set<Connection> connections;
	private MeterReader meterReader;

}
