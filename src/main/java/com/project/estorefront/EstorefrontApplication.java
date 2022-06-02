package com.project.estorefront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class EstorefrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstorefrontApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}
