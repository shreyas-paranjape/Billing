package com.cybercad.billing.domain.tariff;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tariff_slab")
public class TariffSlab {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JoinColumn(name="tariff_class_id")
	TariffClass tariffClass;
	private String code;
	private int fromUnit;
	private int toUnit;
	private float tariff;
	private float minCharges;


	public int getSlabLength() {
		return toUnit - fromUnit;
	}

	public float getSlabCharges() {
		return getSlabLength() * tariff;
	}

	public float getCharges(float units) {
		return units * getTariff();
	}

	public float getTariff() {
		return tariff;
	}

	public void setTariff(float tariff) {
		this.tariff = tariff;
	}

	public int getToUnit() {
		return toUnit;
	}

	public void setToUnit(int toUnit) {
		this.toUnit = toUnit;
	}

	public int getFromUnit() {
		return fromUnit;
	}

	public void setFromUnit(int fromUnit) {
		this.fromUnit = fromUnit;
	}

}
