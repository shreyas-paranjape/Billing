package com.cybercad.billing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybercad.billing.domain.Message;
import com.cybercad.billing.repository.MessageRepository;

@RestController
@RequestMapping("/api")
public class Greeter {

	@Autowired
	MessageRepository messageService;

	@RequestMapping("/")
	public String greet() {
		for (Message msg : messageService.findByText("first")) {
			if (msg.getNextMessage() != null)
				return msg.getNextMessage().getText();
		}
		return "Default";
	}
}
