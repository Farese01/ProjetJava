package com.example.Projet.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
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
