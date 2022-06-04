package com.project.eStorefront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class EStorefrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(EStorefrontApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/error")
	public String error() {
		return "Greetings from Spring Boot!";
	}

}
