package com.cybercad.billing.domain.conn.reading;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface FaultRepository extends
		PagingAndSortingRepository<Fault, Integer> {

}
