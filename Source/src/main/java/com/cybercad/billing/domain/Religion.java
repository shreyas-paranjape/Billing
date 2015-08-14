package com.cybercad.billing.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

@Entity
public class Religion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Length(max=75)
	private String religion;
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
}
