package com.cybercad.billing.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cybercad.billing.domain.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

	List<Message> findByText(String text);

}
