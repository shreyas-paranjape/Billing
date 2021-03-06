package com.cybercad.billing.domain.people;
// Generated 23 Sep, 2015 4:46:26 PM by Hibernate Tools 3.2.4.GA


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cybercad.billing.domain.conn.Connection;

/**
 * ConsConn generated by hbm2java
 */
@Entity
@Table(name="cons_conn"
    ,catalog="billing"
)
public class ConsConn  implements java.io.Serializable {


     private int id;
     private Consumer consumer;
     private Connection connection;
     private Date activationDate;
     private Date terminationDate;

    public ConsConn() {
    }

	
    public ConsConn(int id, Consumer consumer, Connection connection) {
        this.id = id;
        this.consumer = consumer;
        this.connection = connection;
    }
    public ConsConn(int id, Consumer consumer, Connection connection, Date activationDate, Date terminationDate) {
       this.id = id;
       this.consumer = consumer;
       this.connection = connection;
       this.activationDate = activationDate;
       this.terminationDate = terminationDate;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="consumer_id", nullable=false)
    public Consumer getConsumer() {
        return this.consumer;
    }
    
    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="connection_id", nullable=false)
    public Connection getConnection() {
        return this.connection;
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="activation_date", length=10)
    public Date getActivationDate() {
        return this.activationDate;
    }
    
    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="termination_date", length=10)
    public Date getTerminationDate() {
        return this.terminationDate;
    }
    
    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }




}


