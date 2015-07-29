package com.cybercad.billing.domain.bill;

import java.util.List;

import com.cybercad.billing.domain.conn.ConnectionType;
import com.cybercad.billing.domain.tariff.TariffSlab;

/**
 * @author shreyas
 *
 *         Calculates the charges for units consumed
 * 
 */
public class WaterChargesCalculator {

	public static long calculateWaterCharges(long units, int period,
			ConnectionType connType) {
		final double chargesPerDay = getChargesPerDay(
				getUnitsPerDay(units, period), connType);
		return Math.round(chargesPerDay * period);
	}

	private static double getChargesPerDay(float unitsPerDay,
			ConnectionType connType) {
		final TariffSlab highestSlab = getHighestSlab(unitsPerDay, connType
				.getTariffClass().getTariffSlabs());
		final double highestSlabCharges = getChargesUntilSlab(
				highestSlab.getToUnit(), connType.getTariffClass()
						.getTariffSlabs());
		final double offsetCharges = getChargesForUnitsInSlab(
				(unitsPerDay - highestSlab.getFromUnit()), highestSlab);
		return highestSlabCharges + offsetCharges;
	}

	private static float getUnitsPerDay(long units, int period) {
		return units / period;
	}

	private static TariffSlab getHighestSlab(float units,
			List<TariffSlab> tariffSlabs) {
		for (TariffSlab tariffSlab : tariffSlabs) {
			if (units / tariffSlab.getToUnit() <= 1) {
				return tariffSlab;
			}
		}
		// Beyond last slab
		return tariffSlabs.get(tariffSlabs.size() - 1);
	}

	private static double getChargesUntilSlab(int highestSlabLimit,
			List<TariffSlab> tariffSlabs) {
		double charges = 0;
		for (TariffSlab slab : tariffSlabs) {
			if (slab.getToUnit() <= highestSlabLimit) {
				charges += slab.getSlabCharges();
			}
		}
		return charges;
	}

	private static double getChargesForUnitsInSlab(float units, TariffSlab slab) {
		return units * slab.getTariff();
	}

}