package com.cybercad.billing.domain.conn;

import com.cybercad.billing.domain.tariff.TariffClass;

public class ConnectionType {

	private String code;
	private String desciption;
	private int minUnits;
	private int sewageChargeMultiplier;
	private TariffClass tariffClass;

	public long getSewageCharges(long waterCharges) {
		return sewageChargeMultiplier * waterCharges;
	}

	public TariffClass getTariffClass() {
		return tariffClass;
	}

	public void setTarrifClass(TariffClass tarrifClass) {
		this.tariffClass = tarrifClass;
	}

	public int getMinUnits() {
		return minUnits;
	}

	public void setMinUnits(int minUnits) {
		this.minUnits = minUnits;
	}

}
