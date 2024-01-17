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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Collections;
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
            Map<String, Float> closeValues = stockEntity.getClose();
            Map<String, Float> openValues = stockEntity.getOpen();
            Map<String, Float> lowValues = stockEntity.getLow();
            Map<String, Float> highValues = stockEntity.getHigh();
            Map<String, Float> volumeValues = stockEntity.getVolume();
            Map<String, Float> countMap = stockEntity.getCount();

            countMap.put(targetDate, countMap.getOrDefault(targetDate, 0f) + 1);
            if (closeValues.containsKey(targetDate)) {
                Float closeValue = closeValues.get(targetDate);
                Float openValue = openValues.get(targetDate);
                Float lowValue = lowValues.get(targetDate);
                Float highValue = highValues.get(targetDate);
                Float volumeValue = volumeValues.get(targetDate);
                return StockPriceDTO.builder()
                        .symbol(symbol)
                        .date(targetDate)
                        .openValue(openValue)
                        .closeValue(closeValue)
                        .lowValue(lowValue)
                        .highValue(highValue)
                        .volumeValue(volumeValue)
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
                return stockEntity.getOpen().entrySet().stream()
                        .filter(entry -> {
                            String currentDate = entry.getKey();
                            return currentDate.compareTo(dateFrom) >= 0 && currentDate.compareTo(dateTo) <= 0;
                        })
                        .map(entry -> StockPriceDTO.builder()
                                .symbol(symbol)
                                .date(entry.getKey())
                                .openValue(entry.getValue())
                                .closeValue(stockEntity.getClose().get(entry.getKey()))
                                .lowValue(stockEntity.getLow().get(entry.getKey()))
                                .highValue(stockEntity.getHigh().get(entry.getKey()))
                                .volumeValue(stockEntity.getVolume().get(entry.getKey()))
                                .build())
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            // Handle any exceptions (e.g., parsing, database access)
            log.error("Error retrieving stock prices", e);
        }

        return Collections.emptyList();
    }
}

