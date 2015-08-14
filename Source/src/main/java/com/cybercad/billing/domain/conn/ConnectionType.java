package com.cybercad.billing.domain.conn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cybercad.billing.domain.tariff.TariffClass;
@Entity
@Table(name="conn_type")
public class ConnectionType {
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String desciption;
	private int minUnits;
	private int sewageChargeMultiplier;
	@ManyToOne
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

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public int getSewageChargeMultiplier() {
		return sewageChargeMultiplier;
	}

	public void setSewageChargeMultiplier(int sewageChargeMultiplier) {
		this.sewageChargeMultiplier = sewageChargeMultiplier;
	}

	public void setTariffClass(TariffClass tariffClass) {
		this.tariffClass = tariffClass;
	}

}
