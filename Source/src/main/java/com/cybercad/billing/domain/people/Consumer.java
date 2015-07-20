package com.cybercad.billing.domain.people;

import java.util.Set;

import com.cybercad.billing.domain.comms.CommunicationDetails;
import com.cybercad.billing.domain.conn.Connection;

public class Consumer {

	private PersonalDetails personalDetails;
	private CommunicationDetails communicationDetails;

	private String consumerCode;
	
	private Set<Connection> connections;
	private int members;

}
