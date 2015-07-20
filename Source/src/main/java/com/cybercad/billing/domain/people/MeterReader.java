package com.cybercad.billing.domain.people;

import java.util.Set;

import com.cybercad.billing.domain.comms.CommunicationDetails;
import com.cybercad.billing.domain.conn.reading.ReadingCycle;

public class MeterReader {

	private PersonalDetails personalDetails;
	private CommunicationDetails communicationDetails;
	private ReadingCycle currentReadingCycle;
	private Set<ReadingCycle> previousReadindCycles;

	public void download() {
	}

	public void upload() {
	}

	public boolean login(String username, String password) {
		return false;
	}

}
