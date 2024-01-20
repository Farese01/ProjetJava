package com.example.Projet.service.impl;

import com.example.Projet.domain.Stock;
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

public class StockServiceImpl implements StockService{
    private final StockEntityRepository stockEntityRepository;

    @Override
    public List<StockValues> findAll() {
        List<StockEntity> stockEntities = stockEntityRepository.findAll();
        return StockValuesMapper.toList(stockEntities);
    }

    public void fetchDataAndSave(String symbol) {
        // Step 1: Use a REST client to fetch JSON data from the API
        String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+symbol+"&apikey=029SGZKEAAOV9PXE"; // Replace with the actual API URL
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

    



    /*@Override
>>>>>>> Stashed changes
    public List<Stock> findAll() {
        List<StockEntity> entities = stockEntityRepository.findAll();
        return StockMapper.toList(entities);
    }

    @Override
    public Optional<Stock> findBySymbol(String symbol) {
        Optional<StockEntity> entityOptional = stockEntityRepository.findBySymbol(symbol);
        return entityOptional.map(StockMapper::toStock);
    }

    @Override
    public Optional<StockValues> findStockValuesBySymbolAndDate(String symbol, String date) {
        Optional<StockEntity> entityOptional = stockEntityRepository.findBySymbol(symbol);
        return entityOptional.map(entity -> StockValuesMapper.toStockValues(entity.getStockValuesMap().get(date)));
    }
<<<<<<< Updated upstream
=======


    public Map.Entry<String, Float> findMostSearchedStock() {
        try {
            List<StockEntity> stockEntities = stockEntityRepository.findAll();

            // Map to store stock symbol and its total count
            Map<String, Float> totalCountMap = new HashMap<>();

            // Iterate through each stock entity and sum up the counts
            stockEntities.forEach(stockEntity -> {
                stockEntity.getCount().forEach((date, count) -> {
                    totalCountMap.put(stockEntity.getSymbol(), totalCountMap.getOrDefault(stockEntity.getSymbol(), 0f) + count);
                });
            });

            return Collections.max(totalCountMap.entrySet(), Map.Entry.comparingByValue());
        } catch (Exception e) {
            // Handle any exceptions (e.g., database access)
            log.error("Error finding most searched stock", e);
            return null;
        }
    }*/



}
