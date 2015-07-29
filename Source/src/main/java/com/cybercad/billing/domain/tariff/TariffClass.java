package com.cybercad.billing.domain.tariff;

import java.util.Date;
import java.util.List;

public class TariffClass {

	private Long id;
	private String description;
	private Date effectiveDate;
	private List<TariffSlab> tariffSlabs;

	public List<TariffSlab> getTariffSlabs() {
		return tariffSlabs;
	}

	public void setTariffSlabs(List<TariffSlab> tariffSlabs) {
		this.tariffSlabs = tariffSlabs;
	}

	public TariffSlab getHighestSlab(float units) {
		for (TariffSlab tariffSlab : tariffSlabs) {
			if (units / tariffSlab.getToUnit() <= 1) {
				return tariffSlab;
			}
		}
		// Beyond last slab
		return tariffSlabs.get(tariffSlabs.size() - 1);
	}

	public double getChargesUntilSlab(float units) {
		double charges = 0;
		for (TariffSlab slab : tariffSlabs) {
			if (units / slab.getToUnit() <= 1) {
				charges += slab.getSlabCharges();
			}
		}
		return charges;
	}

	public double getChargesPerDay(float unitsPerDay) {
		final TariffSlab highestSlab = getHighestSlab(unitsPerDay);
		final double highestSlabCharges = getChargesUntilSlab(highestSlab
				.getToUnit());
		final float offsetUnits = unitsPerDay - highestSlab.getFromUnit();
		final double offsetCharges = highestSlab.getCharges(offsetUnits);
		return highestSlabCharges + offsetCharges;
	}

}
