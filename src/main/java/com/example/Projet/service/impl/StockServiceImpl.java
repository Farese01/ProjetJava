package com.example.Projet.service.impl;

import com.example.Projet.domain.StockPriceDTO;
import com.example.Projet.repository.StockEntityRepository;
import com.example.Projet.service.StockService;
import com.example.Projet.domain.Stock;
import com.example.Projet.mapper.StockMapper;
import com.example.Projet.entity.StockEntity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j

public class StockServiceImpl implements StockService {
    private final StockEntityRepository stockEntityRepository;

    @Override
    public List<Stock> findAll() {
        List<StockEntity> entities = stockEntityRepository.findAll();
        return StockMapper.toList(entities);
    }

    @Override
    public Stock findBySymbol(String symbol) throws Exception {
        Optional<StockEntity> stock = Optional.ofNullable(stockEntityRepository.findBySymbol(symbol));
        StockEntity entity = stock.orElseThrow(()-> new Exception("Symbol not found"));
        return StockMapper.toStock(entity);

    }

    public StockPriceDTO getStockPriceByDate(String symbol, String targetDate) {
        Optional<StockEntity> stockEntityOptional = Optional.ofNullable(stockEntityRepository.findBySymbol(symbol));

        if (stockEntityOptional.isPresent()) {
            StockEntity stockEntity = stockEntityOptional.get();
            Map<String, Float> countMap = stockEntity.getCount();

            countMap.put(targetDate, countMap.getOrDefault(targetDate, 0f) + 1);
            if (stockEntity.getClose().containsKey(targetDate)) {
                return StockPriceDTO.builder()
                        .symbol(symbol)
                        .date(targetDate)
                        .openValue(stockEntity.getOpen().get(targetDate))
                        .closeValue(stockEntity.getClose().get(targetDate))
                        .lowValue(stockEntity.getLow().get(targetDate))
                        .highValue(stockEntity.getHigh().get(targetDate))
                        .volumeValue(stockEntity.getVolume().get(targetDate))
                        .build();
            }
        }
        return null;
    }

    @Override
    public List<StockPriceDTO> getStockPricesBetweenDates(String symbol, String dateFrom, String dateTo) {
        try {
            Optional<StockEntity> stockEntityOptional = Optional.ofNullable(stockEntityRepository.findBySymbol(symbol));

            if (stockEntityOptional.isPresent()) {
                StockEntity stockEntity = stockEntityOptional.get();
                Map<String, Float> countMap = stockEntity.getCount(); // Assuming count is stored in a map

                return stockEntity.getOpen().entrySet().stream()
                        .filter(entry -> {
                            String currentDate = entry.getKey();
                            return currentDate.compareTo(dateFrom) >= 0 && currentDate.compareTo(dateTo) <= 0;
                        })
                        .map(entry -> {
                            String currentDate = entry.getKey();
                            countMap.put(currentDate, countMap.getOrDefault(currentDate, 0f) + 1);

                            return StockPriceDTO.builder()
                                    .symbol(symbol)
                                    .date(currentDate)
                                    .openValue(entry.getValue())
                                    .closeValue(stockEntity.getClose().get(currentDate))
                                    .lowValue(stockEntity.getLow().get(currentDate))
                                    .highValue(stockEntity.getHigh().get(currentDate))
                                    .volumeValue(stockEntity.getVolume().get(currentDate))
                                    .build();
                            })
                        .collect(Collectors.toList());
            }
        }
        catch (Exception e) {
            log.error("Error retrieving stock prices", e);
        }

        return Collections.emptyList();
    }


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
    }
}

