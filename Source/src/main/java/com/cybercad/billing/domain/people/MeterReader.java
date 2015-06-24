package com.cybercad.billing.domain.people;

import java.util.Set;

import com.cybercad.billing.domain.comms.CommunicationDetails;
import com.cybercad.billing.domain.geoclass.Ward;

public class MeterReader extends Person {
	private Set<Ward> wards;
	
	private void download(){
	}
}
