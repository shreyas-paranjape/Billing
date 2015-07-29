package com.cybercad.billing.domain.tariff;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

}
