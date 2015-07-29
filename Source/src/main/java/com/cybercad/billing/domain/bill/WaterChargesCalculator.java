package com.cybercad.billing.domain.bill;

import com.cybercad.billing.domain.conn.ConnectionType;
import com.cybercad.billing.domain.conn.reading.ConnectionReading;

/**
 * @author shreyas
 *
 *         Calculates the charges for units consumed
 * 
 */
public class WaterChargesCalculator {

	public static long calculateWaterCharges(ConnectionReading reading,
			int period, ConnectionType connType) {
		final double chargesPerDay = connType.getTariffClass()
				.getChargesPerDay(reading.getUnitsPerDay(period));
		return Math.round(chargesPerDay * period);
	}

}