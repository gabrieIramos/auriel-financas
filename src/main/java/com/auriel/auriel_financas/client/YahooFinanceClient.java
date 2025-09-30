package com.auriel.auriel_financas.client;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Component
public class YahooFinanceClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiKey = "9ce125ba84msh64fd895d7b767afp147644jsn661ae0189f93"; // ADD no .env depois
    private final String baseUrl = "https://yahoo-finance-api13.p.rapidapi.com/api";

    public String buscarCotacoesBatch(List<String> tickers) {
    String symbols = String.join(",", tickers);
    String url = baseUrl + "?endpoint=quote&symbol=" + symbols;

    HttpHeaders headers = new HttpHeaders();
    headers.set("x-rapidapi-host", "yahoo-finance-api13.p.rapidapi.com");
    headers.set("x-rapidapi-key", apiKey);

    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

    return response.getBody();
}

}
