package com.example.Projet.service.impl;

import com.example.Projet.entity.StockValues;
import com.example.Projet.mapper.StockValuesMapper;
import com.example.Projet.repository.StockEntityRepository;
import com.example.Projet.service.StockService;
import com.example.Projet.domain.Stock;
import com.example.Projet.mapper.StockMapper;
import com.example.Projet.entity.StockEntity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j

public class StockServiceImpl implements StockService{
    private final StockEntityRepository stockEntityRepository;
<<<<<<< Updated upstream
    @Override
=======

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


>>>>>>> Stashed changes
}
