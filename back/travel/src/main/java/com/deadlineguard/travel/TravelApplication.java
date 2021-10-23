package com.deadlineguard.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.lang.StringBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

@RestController
@SpringBootApplication
public class TravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

	// пример запроса
	// http://localhost:8080/?vehicle=Автомобиль&startDate=01.11.2021&endDate=31.11.2021&budget=10000
	@GetMapping("/")
	public ArrayList<ObjectNode> index(@RequestParam String vehicle, @RequestParam String startDate, @RequestParam String endDate, @RequestParam int budget) throws java.io.IOException {
		Travel travel = new Travel(vehicle, startDate, endDate, budget);

		return travel.getSuggestions();
	}
}
