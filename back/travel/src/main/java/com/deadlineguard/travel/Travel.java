package com.deadlineguard.travel;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Travel {
    class Suggestion {
        Integer adultTicketPrice;
        Integer childrenTicketPrice;
        String startDate;
        String endDate;

        public Suggestion(Integer adultTicketPrice, Integer childrenTicketPrice, String startDate, String endDate) {
            this.adultTicketPrice = adultTicketPrice;
            this.childrenTicketPrice = childrenTicketPrice;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Integer getAdultTicketPrice() {
            return this.adultTicketPrice;
        }

        public Integer getChildrenTicketPrice() {
            return this.childrenTicketPrice;
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
        this.suggestions.add(new Suggestion(1000, 500, "15.11.2021", "16.11.2021"));
        this.suggestions.add(new Suggestion(6000, 4000, "15.11.2021", "16.11.2021"));
        this.suggestions.add(new Suggestion(2000, 1000, "15.11.2021", "16.11.2021"));
    }

    void filterSuggestions(String filter) {
        switch (filter) {
            case "minPrice":
                Collections.sort(this.suggestions, new Comparator<Suggestion>() {
                    @Override
                    public int compare(Suggestion a, Suggestion b) {
                        return a.getAdultTicketPrice().compareTo(b.getAdultTicketPrice());
                    }
                });
                break;
        }
    }

    public ArrayList<ObjectNode> getSuggestions(String filter) {
        ObjectMapper mapper = new ObjectMapper();

        ArrayList<ObjectNode> suggestionsNodes = new ArrayList<>();

        fetchSuggestions();
        filterSuggestions(filter);

        for (int i = 0; i < suggestions.size(); i++) {
            ObjectNode suggestion = mapper.createObjectNode();

            suggestion.put("adultTicketPrice", this.suggestions.get(i).getAdultTicketPrice());
            suggestion.put("childrenTicketPrice", this.suggestions.get(i).getChildrenTicketPrice());
            suggestion.put("startDate", this.suggestions.get(i).getStartDate());
            suggestion.put("endDate", this.suggestions.get(i).getEndDate());

            suggestionsNodes.add(suggestion);
        }

        return suggestionsNodes;
    }
}