package com.cybercad.billing.domain.bill;

public enum Fault {

	METER_NOT_WORKING() {
		@Override
		public boolean isMeterFault() {
			return true;
		}
	};

	public abstract boolean isMeterFault();
}
