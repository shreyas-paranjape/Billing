package com.cybercad.billing.domain.conn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "political_zone", catalog = "billing")
public class PoliticalZone implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private Character type;

	public PoliticalZone() {
	}

	public PoliticalZone(int id) {
		this.id = id;
	}

	public PoliticalZone(int id, String name, Character type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", length = 1)
	public Character getType() {
		return this.type;
	}

	public void setType(Character type) {
		this.type = type;
	}

}
