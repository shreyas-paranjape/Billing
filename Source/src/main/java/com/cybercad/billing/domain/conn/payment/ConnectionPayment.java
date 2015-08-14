package com.cybercad.billing.domain.conn.payment;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class ConnectionPayment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private double amount;
	private Date payment_date;
	@ManyToOne
	private PaymentType payment_type;
}
