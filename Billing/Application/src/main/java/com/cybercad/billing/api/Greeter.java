package com.cybercad.billing.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybercad.billing.domain.conn.Connection;

@RestController
@RequestMapping("/api")
public class Greeter {

	// @Autowired
	// MessageRepository messageService;

	@RequestMapping("/")
	public Connection greet() {
		/*
		 * for (Message msg : messageService.findByText("first")) { if
		 * (msg.getNextMessage() != null) return msg.getNextMessage().getText();
		 * }
		 */
		return new Connection();
	}
}
