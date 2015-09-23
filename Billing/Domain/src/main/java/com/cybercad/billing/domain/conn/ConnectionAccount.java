package com.cybercad.billing.domain.conn;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "connection_account", catalog = "billing")
public class ConnectionAccount implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Double reading;
	private Double balance;
	private Double average;
	private Date prevReadDate;
	private Set<Connection> connections = new HashSet<Connection>(0);

	public ConnectionAccount() {
	}

	public ConnectionAccount(int id) {
		this.id = id;
	}

	public ConnectionAccount(int id, Double reading, Double balance,
			Double average, Date prevReadDate, Set<Connection> connections) {
		this.id = id;
		this.reading = reading;
		this.balance = balance;
		this.average = average;
		this.prevReadDate = prevReadDate;
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

	@Column(name = "reading", precision = 22, scale = 0)
	public Double getReading() {
		return this.reading;
	}

	public void setReading(Double reading) {
		this.reading = reading;
	}

	@Column(name = "balance", precision = 22, scale = 0)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "average", precision = 22, scale = 0)
	public Double getAverage() {
		return this.average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "prev_read_date", length = 19)
	public Date getPrevReadDate() {
		return this.prevReadDate;
	}

	public void setPrevReadDate(Date prevReadDate) {
		this.prevReadDate = prevReadDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "connectionAccount")
	public Set<Connection> getConnections() {
		return this.connections;
	}

	public void setConnections(Set<Connection> connections) {
		this.connections = connections;
	}

}
