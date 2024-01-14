package com.example.Projet.mapper;

import com.example.Projet.entity.StockEntity;

public class StockMapper {
    public static Stock toStock(StockEntity entity) {
        return Stock.builder()
                .id(entity.getId())
                .symbol(entity.getSymbol())
                // need to add hashmaps
                .build();
    }
}
