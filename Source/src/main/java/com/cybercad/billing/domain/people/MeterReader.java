package com.cybercad.billing.domain.people;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cybercad.billing.domain.pwd;
import com.cybercad.billing.domain.comms.CommunicationDetails;
import com.cybercad.billing.domain.conn.reading.ReadingCycle;
import com.cybercad.billing.domain.people.auth.User;
@Entity
@Table(name="meter_reader")
public class MeterReader {
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@OneToOne
	private User user;
	@ManyToOne
	private pwd pwd;
	//	private PersonalDetails personalDetails;
	//	private CommunicationDetails communicationDetails;
	//	private ReadingCycle currentReadingCycle;
	//	private Set<ReadingCycle> previousReadindCycles;

	public void download() {
	}

	public void upload() {
	}

	public boolean login(String username, String password) {
		return false;
	}

}
