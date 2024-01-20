package com.example.Projet.domain;

import lombok.Builder;

import java.time.LocalDate;


@Builder
public record StockValues(
        String symbol,
        LocalDate date,
        Float open,
        Float close,
        Float high,
        Float low,
        Long volume
) {

}
