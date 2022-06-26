package com.project.estorefront;

import com.project.estorefront.repository.EstablishDatabaseConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class EstorefrontApplication  implements CommandLineRunner {

	@Value("${test}")
	private String environment; // tested and works

	public static void main(String[] args) {
		SpringApplication.run(EstorefrontApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/error")
	public String error() {
		return "Greetings from Heroku!";
	}

	@Override
	public void run(String... args) throws Exception {
		EstablishDatabaseConnection establishDatabaseConnection = new EstablishDatabaseConnection();
		establishDatabaseConnection.init();
		System.out.println(establishDatabaseConnection.getDataSourceUsername());
		establishDatabaseConnection.closeConnection();
	}
}
