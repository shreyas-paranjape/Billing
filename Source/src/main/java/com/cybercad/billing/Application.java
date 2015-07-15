package com.cybercad.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cybercad.billing.domain.Message;
import com.cybercad.billing.repository.MessageRepository;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application implements CommandLineRunner {

	@Autowired
	MessageRepository messageService;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.setShowBanner(false);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		Message first = new Message("first");
		Message second = new Message("second");
		messageService.save(second);
		first.setNextMessage(second);
		messageService.save(first);
	}

}
