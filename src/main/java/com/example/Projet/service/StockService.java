package com.example.Projet.service;

import com.example.Projet.domain.StockPriceDTO;
import com.example.Projet.domain.StockValues;

import java.util.List;
import java.util.Map;

public interface StockService {
    List<StockValues> findAll();
    void fetchDataAndSave(String symbol);

    StockPriceDTO getStockPriceByDate(String symbol, String targetDate);
    List<StockPriceDTO> getStockPricesBetweenDates(String symbol, String dateFrom, String dateTo);
    Map.Entry<String, Float> findMostSearchedStock();
}
