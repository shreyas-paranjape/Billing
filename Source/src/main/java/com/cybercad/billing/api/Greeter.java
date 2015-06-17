package com.cybercad.billing.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Greeter {

	@RequestMapping("/")
	public String greet(){
		return "Hello";
	}
}
