package com.example.Projet.mapper;

import com.example.Projet.domain.StockValues;
import com.example.Projet.entity.StockEntity;
import com.example.Projet.entity.DailyStockPrice;

import java.util.List;
import java.util.stream.Collectors;

public class StockValuesMapper {

    public static StockValues toStockValues(StockEntity stockEntity) {
        return StockValues.builder()
                .symbol(stockEntity.getSymbol())
                .date(stockEntity.getDailyPrices().stream().map(DailyStockPrice::getDate).findFirst().orElse(null))
                .open(stockEntity.getDailyPrices().stream().map(DailyStockPrice::getOpen).reduce(Float::sum).orElse(0.0f))
                .close(stockEntity.getDailyPrices().stream().map(DailyStockPrice::getClose).reduce(Float::sum).orElse(0.0f))
                .high(stockEntity.getDailyPrices().stream().map(DailyStockPrice::getHigh).reduce(Float::sum).orElse(0.0f))
                .low(stockEntity.getDailyPrices().stream().map(DailyStockPrice::getLow).reduce(Float::sum).orElse(0.0f))
                .volume(stockEntity.getDailyPrices().stream().mapToLong(DailyStockPrice::getVolume).sum())
                .build();

    }

    public static List<StockValues> toList(List<StockEntity> stockEntityList) {
        return stockEntityList.stream().map(StockValuesMapper::toStockValues).collect(Collectors.toList());
    }
}
