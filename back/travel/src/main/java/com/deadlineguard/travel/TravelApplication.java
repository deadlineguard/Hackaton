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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@SpringBootApplication
public class TravelApplication {

	// в getmapping прописывается адрес, по которому можно получить данные
	// здесь например GET запрос на http://localhost:8080/
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

	// здесь ответ на GET запрос к адресу http://localhost:8080/test
	// возвращаемый тип должен быть ObjectNode, чтобы вернуть JSON
	@GetMapping("/test")
	public ObjectNode test()  throws java.io.IOException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();

		// получится
		// {
		// 		"key": "value",
		// 		"foo": "bar",
		//      "number": 42
		// }
		objectNode.put("key", "value");
		objectNode.put("foo", "bar");
		objectNode.put("number", 42);

		// получим данные с гугла
		Document doc = Jsoup.connect("https://google.com/").get();
		Element someText = doc.select(".ktLKi").first();

		// во второй аргумент можно передавать строчку, которую пропарсили
		objectNode.put("googleAnswer", someText.text());

		return objectNode;
	}

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

}
