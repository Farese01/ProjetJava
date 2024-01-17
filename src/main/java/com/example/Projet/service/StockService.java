package com.example.Projet.service;

import com.example.Projet.domain.Stock;
import com.example.Projet.domain.StockPriceDTO;
import com.example.Projet.entity.StockEntity;

import java.util.List;
import java.util.Optional;

public interface StockService {
    List<Stock> findAll();
    Stock findBySymbol(String symbol) throws Exception;
    StockPriceDTO getStockPriceByDate(String symbol, String targetDate);
    List<StockPriceDTO> getStockPricesBetweenDates(String symbol, String dateFrom, String dateTo);


}
