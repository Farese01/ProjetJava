package com.example.Projet.mapper;

import com.example.Projet.entity.StockEntity;
import com.example.Projet.domain.Stock;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StockMapper {
    public static Stock toStock(StockEntity entity) {
        return Stock.builder()
                .id(entity.getId())
                .symbol(entity.getSymbol())
                .stockValuesMap(entity.getStockValuesMap().entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> StockValuesMapper.toStockValues(e.getValue()))))
                .build();
    }

    public static List<Stock> toList(List<StockEntity> stockEntityList) {
        return stockEntityList.stream().map(StockMapper::toStock).collect(Collectors.toList());
    }

}
