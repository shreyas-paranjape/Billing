package com.cybercad.billing.domain.conn;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pwd_class", catalog = "billing")
public class PwdClass implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String description;
	private String code;
	private Integer lft;
	private Integer rgt;
	private Set<Connection> connections = new HashSet<Connection>(0);

	public PwdClass() {
	}

	public PwdClass(int id) {
		this.id = id;
	}

	public PwdClass(int id, String description, String code, Integer lft,
			Integer rgt, Set<Connection> connections) {
		this.id = id;
		this.description = description;
		this.code = code;
		this.lft = lft;
		this.rgt = rgt;
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

	@Column(name = "description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "code", length = 10)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "lft")
	public Integer getLft() {
		return this.lft;
	}

	public void setLft(Integer lft) {
		this.lft = lft;
	}

	@Column(name = "rgt")
	public Integer getRgt() {
		return this.rgt;
	}

	public void setRgt(Integer rgt) {
		this.rgt = rgt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pwdClass")
	public Set<Connection> getConnections() {
		return this.connections;
	}

	public void setConnections(Set<Connection> connections) {
		this.connections = connections;
	}

}
