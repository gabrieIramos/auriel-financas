// package com.auriel.auriel_financas.client;

// import org.springframework.stereotype.Component;
// import yahoofinance.Stock; // Importar do novo pacote
// import yahoofinance.YahooFinance; // Importar do novo pacote

// import java.io.IOException;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// @Component
// public class YahooFinanceClient {

//     // O método agora retorna um Mapa de Ticker -> Cotação
//     public Map<String, Double> buscarCotacoesBatch(List<String> tickers) {
        
//         // Converte a lista de tickers para um array, que é o que a API espera
//         String[] tickersArray = tickers.toArray(new String[0]);
        
//         Map<String, Double> cotacoes = new HashMap<>();

//         try {
//             // Chamada ÚNICA à API do Yahoo Finance, sem a RapidAPI
//             Map<String, Stock> stocks = YahooFinance.get(tickersArray, true);

//             for (Map.Entry<String, Stock> entry : stocks.entrySet()) {
//                 String ticker = entry.getKey();
//                 Stock stock = entry.getValue();

//                 // Garante que a cotação é válida e o mercado está aberto/fechado
//                 if (stock != null && stock.getQuote().getPrice() != null) {
//                     cotacoes.put(ticker, stock.getQuote().getPrice().doubleValue());
//                 } else {
//                     System.err.println("A cotação para o ticker " + ticker + " não foi encontrada ou é inválida.");
//                 }
//             }

//         } catch (IOException e) {
//             System.err.println("Erro ao buscar cotações na Yahoo Finance: " + e.getMessage());
//             // Em caso de erro, retorna um mapa vazio ou lança uma exceção de negócio.
//         }
        
//         return cotacoes;
//     }
// }