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

import javax.management.Query;

@RestController
@SpringBootApplication
public class TravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

	// результаты для анапы
	// возвращает массив с именами отелей и их стоимостью
	// запрос принимает два параметра, писать запрос нужно так
	// http://localhost:8080/get-anapa-hotels?adultsNumber=2&childrenNumber=0
	// где число взрослых и детей определяется параметрами
	@GetMapping("/get-anapa-hotels")
	public ArrayList<ObjectNode> getAnapa(@RequestParam int adultsNumber, @RequestParam int childrenNumber) throws java.io.IOException {
		ObjectMapper mapper = new ObjectMapper();

		Document doc = Jsoup.connect("https://www.booking.com/searchresults.ru.html?label=ruru-edge-ntp-topsites-curate-ana&sid=ff8afd0e18c7098c4494d785cf4f5dbe&aid=1535069&sb=1&src_elem=sb&error_url=https%3A%2F%2Fwww.booking.com%2Fdealspage.ru.html%3Faid%3D1535069%3Blabel%3Druru-edge-ntp-topsites-curate-ana%3Bsid%3Dff8afd0e18c7098c4494d785cf4f5dbe%3Btmpl%3Ddeal_finder%3Bsig%3Dv1OzaVKl4r%3B&order=price&lpsrc=sb&ss=%D0%B0%D0%BD%D0%B0%D0%BF%D0%B0&is_ski_area=0&checkin_year=2021&checkin_month=10&checkin_monthday=22&checkout_year=2021&checkout_month=10&checkout_monthday=23&no_rooms=1&group_adults=" + adultsNumber + "&group_children=" + childrenNumber + "&b_h4u_keep_filters=&from_sf=1").get();
		Elements hotelNames = doc.select(".sr-hotel__name");
		Elements hotelPrices = doc.select(".prco-valign-middle-helper");

		ArrayList<ObjectNode> hotelNamesAndPrices = new ArrayList<>();

		// в цикле создается объект JSON, который добавляется в массив
		// получается как бы массив из объектов
		// выглядит это примерно так
		// [
		// 		{
		// 			"hotelName":"По-свойски",
		// 			"hotelPrice":"RUB 690"
		// 		},
		// 		{
		// 			"hotelName":"Svetlana рядом с парком",
		// 			"hotelPrice":"RUB 799"
		// 		}
		// ]
		for (int i = 0; i < hotelNames.size(); i++) {
			ObjectNode objectNode = mapper.createObjectNode();

			objectNode.put("hotelName", hotelNames.get(i).text());
			objectNode.put("hotelPrice", hotelPrices.get(i).text());

			hotelNamesAndPrices.add(objectNode);
		}

		return hotelNamesAndPrices;
	}

}
