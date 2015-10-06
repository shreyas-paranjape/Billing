package com.cybercad.billing.domain.devices;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "meter_status", catalog = "billing")
public class MeterStatus implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private Set<MeterConditionHistory> meterConditionHistories = new HashSet<MeterConditionHistory>(
			0);

	public MeterStatus() {
	}

	public MeterStatus(String description) {
		this.description = description;
	}

	public MeterStatus(String description,
			Set<MeterConditionHistory> meterConditionHistories) {
		this.description = description;
		this.meterConditionHistories = meterConditionHistories;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "description", unique = false, nullable = false, length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meterStatus")
	public Set<MeterConditionHistory> getMeterConditionHistories() {
		return this.meterConditionHistories;
	}

	public void setMeterConditionHistories(
			Set<MeterConditionHistory> meterConditionHistories) {
		this.meterConditionHistories = meterConditionHistories;
	}

}
