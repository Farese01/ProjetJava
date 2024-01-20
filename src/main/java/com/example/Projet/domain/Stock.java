package com.example.Projet.domain;

import lombok.Builder;

import java.util.Map;
import java.util.UUID;
@Builder
public record Stock(
        String symbol
) {
}
