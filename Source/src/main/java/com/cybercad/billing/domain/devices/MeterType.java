package com.cybercad.billing.domain.devices;

public class MeterType {

	private String name;
	private String description;
	private int size;
	private float rent;

	public float getMeterRent(int period) {
		return rent * period;
	}

}
