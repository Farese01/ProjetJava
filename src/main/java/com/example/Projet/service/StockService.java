package com.example.Projet.service;

import com.example.Projet.domain.Stock;
import com.example.Projet.entity.StockValues;

import java.util.List;
import java.util.Optional;

public interface StockService {
    List<Stock> findAll();
    Optional<Stock> findBySymbol(String symbol);
    Optional<StockValues> findStockValuesBySymbolAndDate(String symbol, String date);

}
