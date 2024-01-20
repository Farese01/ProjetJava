package com.example.Projet.mapper;

import com.example.Projet.domain.StockValues;
import com.example.Projet.entity.StockEntity;
import com.example.Projet.entity.DailyStockPrice;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class StockValuesMapper {
        public static StockValues toStockValues(StockEntity stockEntity) {
            List<LocalDate> dates = stockEntity.getDailyPrices().stream().map(DailyStockPrice::getDate).collect(Collectors.toList());
            List<Float> open = stockEntity.getDailyPrices().stream().map(DailyStockPrice::getOpen).collect(Collectors.toList());
            List<Float> close = stockEntity.getDailyPrices().stream().map(DailyStockPrice::getClose).collect(Collectors.toList());
            List<Float> high = stockEntity.getDailyPrices().stream().map(DailyStockPrice::getHigh).collect(Collectors.toList());
            List<Float> low = stockEntity.getDailyPrices().stream().map(DailyStockPrice::getLow).collect(Collectors.toList());
            List<Long> volume = stockEntity.getDailyPrices().stream().map(DailyStockPrice::getVolume).collect(Collectors.toList());

            return StockValues.builder()
                    .symbol(stockEntity.getSymbol())
                    .dates(dates)
                    .open(open)
                    .close(close)
                    .high(high)
                    .low(low)
                    .volume(volume)
                    .build();
        }

    public static List<StockValues> toList(List<StockEntity> stockEntityList) {
        return stockEntityList.stream().map(StockValuesMapper::toStockValues).collect(Collectors.toList());
    }
}
