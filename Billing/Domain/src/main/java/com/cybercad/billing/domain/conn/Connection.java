package com.cybercad.billing.domain.conn;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cybercad.billing.domain.conn.reading.ConnReading;
import com.cybercad.billing.domain.devices.Meter;
import com.cybercad.billing.domain.people.ConsConn;

@Entity
@Table(name = "connection", catalog = "billing")
public class Connection implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Location location;
	private ConnectionCategory connectionCategory;
	private ConnectionAccount connectionAccount;
	private Constituency constituency;
	private PwdClass pwdClass;
	private String connCode;
	private Boolean status;
	private int politicalZoneId;
	private Integer noOfUsers;
	private Set<Meter> meters = new HashSet<Meter>(0);
	private Set<ConsConn> consConns = new HashSet<ConsConn>(0);
	private Set<ConnReading> connReadings = new HashSet<ConnReading>(0);

	public Connection() {
	}

	public Connection(Location location, ConnectionCategory connectionCategory,
			ConnectionAccount connectionAccount, Constituency constituency,
			PwdClass pwdClass, String connCode, int politicalZoneId) {
		this.location = location;
		this.connectionCategory = connectionCategory;
		this.connectionAccount = connectionAccount;
		this.constituency = constituency;
		this.pwdClass = pwdClass;
		this.connCode = connCode;
		this.politicalZoneId = politicalZoneId;
	}

	public Connection(Location location, ConnectionCategory connectionCategory,
			ConnectionAccount connectionAccount, Constituency constituency,
			PwdClass pwdClass, String connCode, Boolean status,
			int politicalZoneId, Integer noOfUsers, Set<Meter> meters,
			Set<ConsConn> consConns, Set<ConnReading> connReadings) {
		this.location = location;
		this.connectionCategory = connectionCategory;
		this.connectionAccount = connectionAccount;
		this.constituency = constituency;
		this.pwdClass = pwdClass;
		this.connCode = connCode;
		this.status = status;
		this.politicalZoneId = politicalZoneId;
		this.noOfUsers = noOfUsers;
		this.meters = meters;
		this.consConns = consConns;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id", nullable = false)
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "connection_category_id", nullable = false)
	public ConnectionCategory getConnectionCategory() {
		return this.connectionCategory;
	}

	public void setConnectionCategory(ConnectionCategory connectionCategory) {
		this.connectionCategory = connectionCategory;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "connection_account_id", nullable = false)
	public ConnectionAccount getConnectionAccount() {
		return this.connectionAccount;
	}

	public void setConnectionAccount(ConnectionAccount connectionAccount) {
		this.connectionAccount = connectionAccount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id", nullable = false)
	public Constituency getConstituency() {
		return this.constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pwd_class_id", nullable = false)
	public PwdClass getPwdClass() {
		return this.pwdClass;
	}

	public void setPwdClass(PwdClass pwdClass) {
		this.pwdClass = pwdClass;
	}

	@Column(name = "conn_code", unique = true, nullable = false, length = 15)
	public String getConnCode() {
		return this.connCode;
	}

	public void setConnCode(String connCode) {
		this.connCode = connCode;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "political_zone_id", nullable = false)
	public int getPoliticalZoneId() {
		return this.politicalZoneId;
	}

	public void setPoliticalZoneId(int politicalZoneId) {
		this.politicalZoneId = politicalZoneId;
	}

	@Column(name = "no_of_users")
	public Integer getNoOfUsers() {
		return this.noOfUsers;
	}

	public void setNoOfUsers(Integer noOfUsers) {
		this.noOfUsers = noOfUsers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "connection")
	public Set<Meter> getMeters() {
		return this.meters;
	}

	public void setMeters(Set<Meter> meters) {
		this.meters = meters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "connection")
	public Set<ConsConn> getConsConns() {
		return this.consConns;
	}

	public void setConsConns(Set<ConsConn> consConns) {
		this.consConns = consConns;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "connection")
	public Set<ConnReading> getConnReadings() {
		return this.connReadings;
	}

	public void setConnReadings(Set<ConnReading> connReadings) {
		this.connReadings = connReadings;
	}

}
