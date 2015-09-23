package com.cybercad.billing.domain.conn;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cybercad.billing.domain.tariff.TariffClass;

@Entity
@Table(name = "connection_category", catalog = "billing")
public class ConnectionCategory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private TariffClass tariffClass;
	private String description;
	private Integer minUnits;
	private Float sewageChargeMultiplier;
	private Set<Connection> connections = new HashSet<Connection>(0);

	public ConnectionCategory() {
	}

	public ConnectionCategory(int id, TariffClass tariffClass) {
		this.id = id;
		this.tariffClass = tariffClass;
	}

	public ConnectionCategory(int id, TariffClass tariffClass,
			String description, Integer minUnits, Float sewageChargeMultiplier,
			Set<Connection> connections) {
		this.id = id;
		this.tariffClass = tariffClass;
		this.description = description;
		this.minUnits = minUnits;
		this.sewageChargeMultiplier = sewageChargeMultiplier;
		this.connections = connections;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tariff_class_id", nullable = false)
	public TariffClass getTariffClass() {
		return this.tariffClass;
	}

	public void setTariffClass(TariffClass tariffClass) {
		this.tariffClass = tariffClass;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "min_units")
	public Integer getMinUnits() {
		return this.minUnits;
	}

	public void setMinUnits(Integer minUnits) {
		this.minUnits = minUnits;
	}

	@Column(name = "sewage_charge_multiplier", precision = 12, scale = 0)
	public Float getSewageChargeMultiplier() {
		return this.sewageChargeMultiplier;
	}

	public void setSewageChargeMultiplier(Float sewageChargeMultiplier) {
		this.sewageChargeMultiplier = sewageChargeMultiplier;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "connectionCategory")
	public Set<Connection> getConnections() {
		return this.connections;
	}

	public void setConnections(Set<Connection> connections) {
		this.connections = connections;
	}

}
