package com.example.Projet.domain;

import lombok.Builder;


@Builder

public record StockValues(
        String symbol,
        Float open,
        Float close,
        Float high,
        Float low,
        Long volume
) {

}
