package com.example.Projet.mapper;

import com.example.Projet.domain.StockOverview;
import com.example.Projet.domain.StockValues;
import com.example.Projet.entity.DailyStockPrice;
import com.example.Projet.entity.StockEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class StockOverviewMapper {
    public static StockOverview toStockValues(StockEntity stockEntity) {
        List<LocalDate> dates = stockEntity.getDailyPrices().stream().map(DailyStockPrice::getDate).collect(Collectors.toList());
        List<Float> open = stockEntity.getDailyPrices().stream().map(DailyStockPrice::getOpen).collect(Collectors.toList());
        List<Float> close = stockEntity.getDailyPrices().stream().map(DailyStockPrice::getClose).collect(Collectors.toList());
        List<Float> high = stockEntity.getDailyPrices().stream().map(DailyStockPrice::getHigh).collect(Collectors.toList());
        List<Float> low = stockEntity.getDailyPrices().stream().map(DailyStockPrice::getLow).collect(Collectors.toList());
        List<Long> volume = stockEntity.getDailyPrices().stream().map(DailyStockPrice::getVolume).collect(Collectors.toList());
        List<Integer> count = stockEntity.getDailyPrices().stream().map(DailyStockPrice::getCount).collect(Collectors.toList());

        return StockOverview.builder()
                .symbol(stockEntity.getSymbol())
                .dates(dates)
                .open(open)
                .close(close)
                .high(high)
                .low(low)
                .volume(volume)
                .count(count)
                .build();
    }

    public static List<StockOverview> toList(List<StockEntity> stockEntityList) {
        return stockEntityList.stream().map(StockOverviewMapper::toStockValues).collect(Collectors.toList());
    }
}
