package com.example.Projet.service;

import com.example.Projet.entity.StockEntity;
import java.util.List;

import java.util.Optional;
public interface StockService {
    List<StockEntity> findAll();
    StockEntity findBySymbol(String symbol);

}
