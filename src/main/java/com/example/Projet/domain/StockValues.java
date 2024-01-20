package com.example.Projet.domain;

import lombok.Builder;


@Builder
public record StockValues() {
    static Float open,
    static Float close,
    static Float high,
    static Float low,
    static Float volume,
    static Integer count
}
