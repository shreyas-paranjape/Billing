package com.cybercad.billing.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cybercad.billing.domain.conn.Connection;
import com.cybercad.billing.domain.geoclass.Ward;

public interface ConnectionRepository extends CrudRepository<Connection, Long> {

	List<Connection> findByWard(Ward ward);

}
