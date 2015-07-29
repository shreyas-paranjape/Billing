package com.cybercad.billing.domain.tariff;

public class TariffSlab {

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
