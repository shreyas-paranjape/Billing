package com.cybercad.billing.domain.people;

import java.util.Set;

import com.cybercad.billing.domain.bill.ReadingCycle;
import com.cybercad.billing.domain.comms.CommunicationDetails;
import com.cybercad.billing.domain.geoclass.Ward;

public class MeterReader {

	private PersonalDetails personalDetails;
	private CommunicationDetails communicationDetails;
	private ReadingCycle currentReadingCycle;
	private Set<Ward> wards;

	public void download() {
	}

	public void upload() {
	}

	public boolean login(String username, String password) {
		return false;
	}

}
