package com.example.Projet.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;


@Builder
@Getter
public record StockValues(
        String symbol,
        List<LocalDate> dates,
        List<Float> open,
        List<Float> close,
        List<Float> high,
        List<Float> low,
        List<Long> volume
) {

}
