package com.example.Projet.service.impl;

import com.example.Projet.repository.StockEntityRepository;
import com.example.Projet.service.StockService;

public class StockServiceImpl implements StockService{
    private final StockEntityRepository stockEntityRepository;
    @Override
    public List<StockEntity> findAll() {
        List<StockEntity> entities = stockEntityRepository.findAll();
        return StockMapper.toList(entities);
    }
}
