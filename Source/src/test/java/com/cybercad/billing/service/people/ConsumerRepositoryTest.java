package com.cybercad.billing.service.people;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.cybercad.billing.domain.people.Consumer;

public class ConsumerRepositoryTest {

	@Test
	public void testFindAll() {
		Collection<Consumer> consumers = ConsumerRepository.findAll();
		assertEquals(consumers.size(), 1);
	}

}
