package com.example.Projet.domain;

import java.util.UUID;

public record Stock(
        UUID id,
        String symbol
        // need to add hashmaps
) {
}
