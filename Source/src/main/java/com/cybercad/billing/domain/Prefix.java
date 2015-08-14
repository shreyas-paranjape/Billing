package com.cybercad.billing.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.Mapping;

import com.cybercad.billing.domain.people.Consumer;

@Entity
@Table(name ="prefix")
public class Prefix {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Length(max=75)
	private String prefix;
//	@OneToMany
//	@Mapping()
//	private Consumer consumer;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	
}
