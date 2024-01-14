package com.example.Projet.mapper;

import com.example.Projet.entity.StockEntity;
import com.example.Projet.domain.Stock;

import java.util.List;
import java.util.stream.Collectors;

public class StockMapper {
    public static Stock toStock(StockEntity entity) {
        return Stock.builder()
                .id(entity.getId())
                .symbol(entity.getSymbol())
                .build();
    }

    public static List<Stock> toList(List<StockEntity> stockEntityList) {
        return stockEntityList.stream().map(StockMapper::toStock).collect(Collectors.toList());
    }
    public static StockEntity toStudentEntity(Stock model) {
        return StockEntity.builder()
                .id(model.id())
                .symbol(model.symbol())
                .build();
    }
}
