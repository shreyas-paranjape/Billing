package com.cybercad.billing.domain.devices;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "meter_type")
public class MeterType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String description;
	private int size;
	private float rent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public float getRent() {
		return rent;
	}

	public void setRent(float rent) {
		this.rent = rent;
	}

	public float getMeterRent(int period) {
		return rent * period;
	}

}	
