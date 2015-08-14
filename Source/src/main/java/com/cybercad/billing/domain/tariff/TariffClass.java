package com.cybercad.billing.domain.tariff;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="tariff_class")
public class TariffClass {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToMany(mappedBy="tariffClass")
	private List<TariffSlab> tariffSlabs;
	private String description;
	private Date effectiveDate;
	private Date terminationDate;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

}
