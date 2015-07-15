package com.cybercad.billing.domain.bill;

import java.util.Date;
import java.util.Set;

public class TarrifClass {
	
	private Long id;
	private String description;
	private Date effectiveDate;
	private Set<TariffSlab> tariffs;
	
}
