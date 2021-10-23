package com.deadlineguard.travel;

import java.util.Date;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Travel {
    class Suggestion {
        int price;
        String startDate;
        String endDate;

        public Suggestion(int price, String startDate, String endDate) {
            this.price = price;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public int getPrice() {
            return this.price;
        }

        public String getStartDate() {
            return this.startDate;
        }

        public String getEndDate() {
            return this.endDate;
        }
    }

    String startDate;
    String endDate;
    String vehicle;
    int budget;

    ArrayList<Suggestion> suggestions;

    public Travel(String vehicle, String startDate, String endDate, int budget) {
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;

        this.suggestions = new ArrayList<>();
    }

    void fetchSuggestions() {
        this.suggestions.add(new Suggestion(1000, "15.11.2021", "16.11.2021"));
    }

    public ArrayList<ObjectNode> getSuggestions() {
        ObjectMapper mapper = new ObjectMapper();

        ArrayList<ObjectNode> suggestionsNodes = new ArrayList<>();

        fetchSuggestions();

        for (int i = 0; i < suggestions.size(); i++) {
            ObjectNode suggestion = mapper.createObjectNode();

            suggestion.put("price", this.suggestions.get(i).getPrice());
            suggestion.put("startDate", this.suggestions.get(i).getStartDate());
            suggestion.put("endDate", this.suggestions.get(i).getEndDate());

            suggestionsNodes.add(suggestion);
        }

        return suggestionsNodes;
    }
}