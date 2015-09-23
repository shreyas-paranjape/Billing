package com.cybercad.billing.domain.conn.reading;

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
@Table(name = "fault", catalog = "billing")
public class Fault implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private Set<ConnReading> connReadings = new HashSet<ConnReading>(0);

	public Fault() {
	}

	public Fault(String description, Set<ConnReading> connReadings) {
		this.description = description;
		this.connReadings = connReadings;
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

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fault")
	public Set<ConnReading> getConnReadings() {
		return this.connReadings;
	}

	public void setConnReadings(Set<ConnReading> connReadings) {
		this.connReadings = connReadings;
	}

}
