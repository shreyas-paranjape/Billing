package com.cybercad.billing.domain.bill;

import com.cybercad.billing.domain.conn.Connection;
import com.cybercad.billing.domain.conn.reading.ConnectionReading;

public class UnitsCalculator {

	public static long getUnits(Connection conn, ConnectionReading reading) {
		if (reading.isFault()) {
			return getMinUnits(conn);
		}
		return reading.getUnits();
	}

	private static long getMinUnits(Connection conn) {
		return Math.min(conn.getCurrentAverage(), conn.getConnectionType()
				.getMinUnits());
	}

}
