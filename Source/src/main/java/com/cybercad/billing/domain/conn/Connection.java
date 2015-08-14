package com.cybercad.billing.domain.conn;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cybercad.billing.domain.devices.Meter;
import com.cybercad.billing.domain.people.Consumer;

/**
 * @author shreyas
 * 
 *         Represents a service that needs to be billed
 *
 */
//@Entity
//@Table(name="connection")
public class Connection {
//	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String code;
	private boolean active;

//	@OneToMany(mappedBy = "connection")
//	private Set<ConsumerConnection> consConnection;
	//	private Consumer consumer;

	public Connection() {
		super();
	}
	
	public Connection(String code) {
		super();
		this.code = code;
	}

	private ConnectionType connectionType;
	private ConnectionStatus connectionStatus;
	private Meter activeMeter;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Meter getActiveMeter() {
		return activeMeter;
	}

	public void setActiveMeter(Meter activeMeter) {
		this.activeMeter = activeMeter;
	}
//
//	public Consumer getConsumer() {
//		return consumer;
//	}
//
//	public void setConsumer(Consumer consumer) {
//		this.consumer = consumer;
//	}

	public ConnectionType getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(ConnectionType connectionType) {
		this.connectionType = connectionType;
	}

	public long calculateMinUnits() {
		return Math.min(connectionStatus.getCurrentAverage(),
				getConnectionType().getMinUnits());
	}

	public ConnectionStatus getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(ConnectionStatus connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

}
