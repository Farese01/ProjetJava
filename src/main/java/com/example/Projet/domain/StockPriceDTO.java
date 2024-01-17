package com.example.Projet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Builder
public record StockPriceDTO (
    String symbol,
    String date,
    Float openValue,
    Float closeValue,
    Float lowValue,
    Float highValue,
    Float volumeValue
){

}