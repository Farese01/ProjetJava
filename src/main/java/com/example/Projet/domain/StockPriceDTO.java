package com.example.Projet.domain;

import lombok.Builder;

@Builder
public record StockPriceDTO(
        String symbol,
        String date,
        Float openValue,
        Float closeValue,
        Float lowValue,
        Float highValue,
        Long volumeValue
) {
}
