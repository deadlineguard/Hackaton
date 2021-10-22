package com.deadlineguard.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TravelApplication {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

}
