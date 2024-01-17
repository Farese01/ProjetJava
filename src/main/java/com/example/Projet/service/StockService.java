package com.example.Projet.service;

import com.example.Projet.domain.Stock;
import com.example.Projet.entity.StockEntity;
import java.util.List;

public interface StockService {
    List<Stock> findAll();
    StockEntity findBySymbol(String symbol);


}
