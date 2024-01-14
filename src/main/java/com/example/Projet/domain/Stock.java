package com.example.Projet.domain;

import lombok.Builder;

import java.util.Map;
import java.util.UUID;
@Builder
public record Stock(
        UUID id,
        String symbol,
        Map<String, Float> open,
        Map<String, Float> close,
        Map<String, Float> high,
        Map<String, Float> low,
        Map<String, Float> volume,
        Map<String, Float> count
) {
}
