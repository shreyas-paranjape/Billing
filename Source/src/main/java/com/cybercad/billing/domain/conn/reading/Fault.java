package com.cybercad.billing.domain.conn.reading;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "fault")
public class Fault {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Length(max=10)
	private String faultCode;
	@Length(max=50)
	private String faultName;
	
	public String getFaultCode() {
		return faultCode;
	}
	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}
	public String getFaultName() {
		return faultName;
	}
	public void setFaultName(String faultName) {
		this.faultName = faultName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
