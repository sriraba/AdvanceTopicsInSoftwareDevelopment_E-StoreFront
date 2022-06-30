package com.project.estorefront;

import com.project.estorefront.repository.Authentication;
import com.project.estorefront.repository.IAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.estorefront.repository.Database;

@SpringBootApplication
public class EstorefrontApplication  implements CommandLineRunner {

	@Value("${test}")
	private String environment; // tested and works

	@Autowired
	Database database;

	public static void main(String[] args) {SpringApplication.run(EstorefrontApplication.class, args);}

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
		database.init();
	}
}
