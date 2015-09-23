package com.cybercad.billing.domain.conn;

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
@Table(name = "location", catalog = "billing")
public class Location implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private float latitude;
	private float longitude;
	private String addLine1;
	private String addLine2;
	private String addLine3;
	private String cityVillage;
	private String taluka;
	private String district;
	private String state;
	private String zipcode;
	private Character adminType;
	private Set<Connection> connections = new HashSet<Connection>(0);

	public Location() {
	}

	public Location(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Location(float latitude, float longitude, String addLine1,
			String addLine2, String addLine3, String cityVillage,
			String taluka, String district, String state, String zipcode,
			Character adminType, Set<Connection> connections) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.addLine1 = addLine1;
		this.addLine2 = addLine2;
		this.addLine3 = addLine3;
		this.cityVillage = cityVillage;
		this.taluka = taluka;
		this.district = district;
		this.state = state;
		this.zipcode = zipcode;
		this.adminType = adminType;
		this.connections = connections;
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

	@Column(name = "latitude", nullable = false, precision = 12, scale = 0)
	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude", nullable = false, precision = 12, scale = 0)
	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	@Column(name = "add_line_1", length = 100)
	public String getAddLine1() {
		return this.addLine1;
	}

	public void setAddLine1(String addLine1) {
		this.addLine1 = addLine1;
	}

	@Column(name = "add_line_2", length = 100)
	public String getAddLine2() {
		return this.addLine2;
	}

	public void setAddLine2(String addLine2) {
		this.addLine2 = addLine2;
	}

	@Column(name = "add_line_3", length = 100)
	public String getAddLine3() {
		return this.addLine3;
	}

	public void setAddLine3(String addLine3) {
		this.addLine3 = addLine3;
	}

	@Column(name = "city_village", length = 45)
	public String getCityVillage() {
		return this.cityVillage;
	}

	public void setCityVillage(String cityVillage) {
		this.cityVillage = cityVillage;
	}

	@Column(name = "taluka", length = 45)
	public String getTaluka() {
		return this.taluka;
	}

	public void setTaluka(String taluka) {
		this.taluka = taluka;
	}

	@Column(name = "district", length = 45)
	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "state", length = 45)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "zipcode", length = 45)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "admin_type", length = 1)
	public Character getAdminType() {
		return this.adminType;
	}

	public void setAdminType(Character adminType) {
		this.adminType = adminType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
	public Set<Connection> getConnections() {
		return this.connections;
	}

	public void setConnections(Set<Connection> connections) {
		this.connections = connections;
	}

}
