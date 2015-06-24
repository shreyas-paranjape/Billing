package com.cybercad.billing.domain.geoclass;

import java.util.List;

import com.cybercad.billing.domain.people.Consumer;
import com.cybercad.billing.domain.people.MeterReader;

public class Ward {
	private SubDivision division;
	private List<Consumer> consumers;
	private MeterReader meterReader;
}
