package com.cybercad.billing.domain.devices;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="meter")
public class Meter {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String number;
	@OneToOne
	private MeterType meterType;
	private Date effectiveDate;
	private Date disconnectionDate;
	private int initialReading;
	private boolean isOwnMeter;
	@ManyToOne
	private MeterStatus status;
}
