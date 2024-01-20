package com.example.Projet.domain;

import lombok.Builder;
import lombok.Getter;

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
    public String getSymbol() {
        return symbol;
    }
    public String getDate() {
        return date;
    }
    public float getOpenValue() {
        return openValue;
    }


    public float getCloseValue() {
        return closeValue;
    }

    public float getHighValue() {
    return highValue;}

    public float getLowValue() {
    return lowValue;}
}
