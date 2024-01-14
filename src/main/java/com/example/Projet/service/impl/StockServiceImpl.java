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
    @Override
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
}
