package com.example.Projet.service;

import com.example.Projet.entity.StockEntity;

import java.util.List;

public interface StockService {
    List<StockEntity> findAll();
    void fetchDataAndSave(String symbol);


    /*List<Stock> findAll();
    Stock findBySymbol(String symbol) throws Exception;
    StockPriceDTO getStockPriceByDate(String symbol, String targetDate);
    List<StockPriceDTO> getStockPricesBetweenDates(String symbol, String dateFrom, String dateTo);


    Map.Entry<String, Float> findMostSearchedStock();*/

}
