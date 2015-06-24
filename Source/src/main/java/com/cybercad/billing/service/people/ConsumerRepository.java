package com.cybercad.billing.service.people;

import java.util.Arrays;
import java.util.Collection;

import com.cybercad.billing.domain.geoclass.Ward;
import com.cybercad.billing.domain.people.Consumer;

public class ConsumerRepository {

	public static final Collection<Consumer> findAll() {
		return Arrays.asList(new Consumer[] { new Consumer() });
	}

	public static final Collection<Consumer> findByName(String name) {
		return null;
	}

	public static final Collection<Consumer> findByWard(Ward ward) {
		return null;
	}

	public static final Consumer findByCode(String code) {
		return null;
	}

}
