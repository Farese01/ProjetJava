package com.example.Projet.mapper;

import com.example.Projet.domain.StockValues;
import com.example.Projet.entity.StockValuesEntity;

public class StockValuesMapper {
    public static StockValues toStockValues(StockValuesEntity valuesEntity) {
        return StockValues.builder()
                .open(valuesEntity.getOpen())
                .close(valuesEntity.getClose())
                .high(valuesEntity.getHigh())
                .low(valuesEntity.getLow())
                .volume(valuesEntity.getVolume())
                .count(valuesEntity.getCount())
                .build();
    }
}
