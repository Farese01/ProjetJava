package com.example.Projet.service.impl;

import com.example.Projet.domain.StockPriceDTO;
import com.example.Projet.domain.StockValues;
import com.example.Projet.mapper.StockValuesMapper;
import com.example.Projet.repository.StockEntityRepository;
import com.example.Projet.service.StockService;
import com.example.Projet.entity.StockEntity;
import com.example.Projet.entity.DailyStockPrice;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.io.IOException;

@Service
@AllArgsConstructor
@Slf4j

public class StockServiceImpl implements StockService {
    private final StockEntityRepository stockEntityRepository;

    @Override
    public List<StockValues> findAll() {
        List<StockEntity> stockEntities = stockEntityRepository.findAll();
        return StockValuesMapper.toList(stockEntities);
    }

    public void fetchDataAndSave(String symbol) {
        // Step 1: Use a REST client to fetch JSON data from the API
        String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=029SGZKEAAOV9PXE"; // Replace with the actual API URL
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(apiUrl, String.class);

        // Step 2: Parse JSON data and map it to entity classes
        StockEntity stockEntity = mapJsonToEntity(jsonString);

        // Step 3: Save the entities to the repository
        stockEntityRepository.save(stockEntity);
    }

    private StockEntity mapJsonToEntity(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        StockEntity stockEntity = new StockEntity();

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // Extract meta data
            JsonNode metaDataNode = jsonNode.path("Meta Data");
            stockEntity.setSymbol(metaDataNode.path("2. Symbol").asText());
            stockEntity.setLastRefreshed(LocalDate.parse(metaDataNode.path("3. Last Refreshed").asText()));

            // Extract daily prices
            JsonNode timeSeriesNode = jsonNode.path("Time Series (Daily)");
            Iterator<Map.Entry<String, JsonNode>> datesIterator = timeSeriesNode.fields();

            List<DailyStockPrice> dailyPrices = new ArrayList<>();

            while (datesIterator.hasNext()) {
                Map.Entry<String, JsonNode> dateEntry = datesIterator.next();
                LocalDate date = LocalDate.parse(dateEntry.getKey());
                JsonNode priceData = dateEntry.getValue();

                DailyStockPrice dailyStockPrice = new DailyStockPrice();
                dailyStockPrice.setStock(stockEntity);
                dailyStockPrice.setDate(date);
                dailyStockPrice.setOpen((float) priceData.path("1. open").asDouble());
                dailyStockPrice.setHigh((float) priceData.path("2. high").asDouble());
                dailyStockPrice.setLow((float) priceData.path("3. low").asDouble());
                dailyStockPrice.setClose((float) priceData.path("4. close").asDouble());
                dailyStockPrice.setVolume(priceData.path("5. volume").asLong());

                dailyPrices.add(dailyStockPrice);
            }

            stockEntity.setDailyPrices(dailyPrices);

        } catch (IOException e) {
            // Handle JSON parsing errors
            e.printStackTrace();
        }

        return stockEntity;
    }

    public StockPriceDTO getStockPriceByDate(String symbol, String targetDate) {
        Optional<StockEntity> stockEntityOptional = stockEntityRepository.findBySymbol(symbol);

        if (stockEntityOptional.isPresent()) {
            StockEntity stockEntity = stockEntityOptional.get();

            if (stockEntity.getDailyPrices() != null) {
                Optional<DailyStockPrice> dailyPriceOptional = stockEntity.getDailyPrices().stream()
                        .filter(dailyStockPrice -> dailyStockPrice.getDate().equals(LocalDate.parse(targetDate)))
                        .findFirst();

                if (dailyPriceOptional.isPresent()) {
                    DailyStockPrice dailyStockPrice = dailyPriceOptional.get();

                    dailyStockPrice.setCount(dailyStockPrice.getCount() + 1);

                    return StockPriceDTO.builder()
                            .symbol(symbol)
                            .date(targetDate)
                            .openValue(dailyStockPrice.getOpen())
                            .closeValue(dailyStockPrice.getClose())
                            .lowValue(dailyStockPrice.getLow())
                            .highValue(dailyStockPrice.getHigh())
                            .volumeValue(dailyStockPrice.getVolume())
                            .build();
                }
            }
        }
        return null;
    }

    @Override
    public List<StockPriceDTO> getStockPricesBetweenDates(String symbol, String dateFrom, String dateTo) {
        try {
            Optional<StockEntity> stockEntityOptional = stockEntityRepository.findBySymbol(symbol);

            if (stockEntityOptional.isPresent()) {
                StockEntity stockEntity = stockEntityOptional.get();
                LocalDate fromDate = LocalDate.parse(dateFrom);
                LocalDate toDate = LocalDate.parse(dateTo);

                for (LocalDate currentDate = fromDate; currentDate.isBefore(toDate) || currentDate.isEqual(toDate); currentDate = currentDate.plusDays(1)) {
                    LocalDate finalCurrentDate = currentDate;
                    Optional<DailyStockPrice> dailyPriceOptional = stockEntity.getDailyPrices().stream()
                            .filter(dailyStockPrice -> dailyStockPrice.getDate().equals(LocalDate.parse(finalCurrentDate.toString())))
                            .findFirst();
                    DailyStockPrice dailyStockPrice = dailyPriceOptional.get();
                    dailyStockPrice.setCount(dailyStockPrice.getCount() + 1);
                    ;
                }

                List<StockPriceDTO> stockPrices = new ArrayList<>();

                stockEntity.getDailyPrices().stream()
                        .filter(dailyStockPrice -> {
                            LocalDate currentDate = dailyStockPrice.getDate();
                            return currentDate.isAfter(fromDate.minusDays(1)) && currentDate.isBefore(toDate.plusDays(1));
                        })
                        .forEach(dailyStockPrice -> {
                            stockPrices.add(StockPriceDTO.builder()
                                    .symbol(symbol)
                                    .date(dailyStockPrice.getDate().toString())
                                    .openValue(dailyStockPrice.getOpen())
                                    .closeValue(dailyStockPrice.getClose())
                                    .lowValue(dailyStockPrice.getLow())
                                    .highValue(dailyStockPrice.getHigh())
                                    .volumeValue(dailyStockPrice.getVolume())
                                    .build());
                        });

                return stockPrices;
            }
        } catch (Exception e) {
            log.error("Error retrieving stock prices", e);
        }

        return Collections.emptyList();
    }


    public Map.Entry<String, Float> findMostSearchedStock() {
        try {
            List<StockEntity> stockEntities = stockEntityRepository.findAll();
            Map<String, Float> totalCountMap = new HashMap<>();

            // Iterate through each stock entity and sum up the counts
            stockEntities.forEach(stockEntity -> {
                stockEntity.getDailyPrices().forEach(dailyStockPrice -> {
                    totalCountMap.put(stockEntity.getSymbol(), totalCountMap.getOrDefault(stockEntity.getSymbol(), 0f) + dailyStockPrice.getCount());
                });
            });

            return Collections.max(totalCountMap.entrySet(), Map.Entry.comparingByValue());
        } catch (Exception e) {
            log.error("Error finding most searched stock", e);
            return null;
        }
    }
    public String suggestNextDayTrend(String symbol) {
        try {
            Optional<StockEntity> stockEntityOptional = stockEntityRepository.findBySymbol(symbol);

            if (stockEntityOptional.isPresent()) {
                StockEntity stockEntity = stockEntityOptional.get();
                
                List<DailyStockPrice> historicalData = stockEntity.getDailyPrices();

                if (historicalData.size() >= 2) {
                    double sumReturns = 0.0;
                    for (int i = 1; i < historicalData.size(); i++) {
                        double previousClose = historicalData.get(i - 1).getClose();
                        double currentClose = historicalData.get(i).getClose();
                        double dailyReturn = (currentClose - previousClose) / previousClose;
                        sumReturns += dailyReturn;
                    }
                    double averageDailyReturn = sumReturns / (historicalData.size() - 1);

                    if (averageDailyReturn > 0) {
                        return "Suggesting: Prices may go up.";
                    } else if (averageDailyReturn < 0) {
                        return "Suggesting: Prices may go down.";
                    } else {
                        return "Suggesting: Prices may stay the same.";
                    }
                } else {
                    return "Insufficient data to provide a suggestion.";
                }
            }
        } catch (Exception e) {
            log.error("Error retrieving stock prices", e);
        }

        return "Unable to provide a suggestion.";
    }
}







