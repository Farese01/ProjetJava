package com.example.Projet.service;

import com.example.Projet.domain.StockPriceDTO;
import com.example.Projet.domain.StockValues;

import java.util.List;

public interface StockService {
    List<StockValues> findAll();
    void fetchDataAndSave(String symbol);

    StockPriceDTO getStockPriceByDate(String symbol, String targetDate);


}
