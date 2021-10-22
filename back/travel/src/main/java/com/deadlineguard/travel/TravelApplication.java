package com.deadlineguard.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.lang.StringBuilder;

@RestController
@SpringBootApplication
public class TravelApplication {

	@GetMapping("/")
	public String index() throws java.io.IOException {
		Document doc = Jsoup.connect("https://google.com/").get();

		// точка в select означает class
		// например, если в html есть тег где class="test"
		// то нужно написать doc.select(".test").first()

		// first нужен для взятия первого элемента, так как
		// возвращается массив Elements
		Element someText = doc.select(".ktLKi").first();

		// должно выдать что то про выбросы углекислых газов
		// со странички гугла
		return someText.text();
	}

	@GetMapping("/test")
	public String test() {
		return "test";
	}

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

}
