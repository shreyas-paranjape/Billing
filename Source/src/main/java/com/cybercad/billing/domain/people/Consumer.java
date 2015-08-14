package com.cybercad.billing.domain.people;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cybercad.billing.domain.*;
import com.cybercad.billing.domain.conn.Connection;
import com.cybercad.billing.domain.conn.ConsumerConnection;
@Entity
@Table(name="consumer")
public class Consumer {
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String consumerCode;
	@ManyToOne
	private Prefix prefix;
	private String consumerName;
	private long mobile;
	private String email;
	@ManyToOne
	private Religion religion;
//	@OneToMany(mappedBy = "consumer")
//	private Set<ConsumerConnection> consConnection;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getConsumerCode() {
		return consumerCode;
	}
	public void setConsumerCode(String consumerCode) {
		this.consumerCode = consumerCode;
	}
	public Prefix getPrefix() {
		return prefix;
	}
	public void setPrefix(Prefix prefix) {
		this.prefix = prefix;
	}
	public String getConsumerName() {
		return consumerName;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Religion getReligion() {
		return religion;
	}
	public void setReligion(Religion religion) {
		this.religion = religion;
	}
	
//	private PersonalDetails personalDetails;
//	private CommunicationDetails communicationDetails;
//	private int members;

}
