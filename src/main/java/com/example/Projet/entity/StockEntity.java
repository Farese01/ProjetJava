package com.example.Projet.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "stock")
@Data

public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name="Symbol")
    private String symbol;
    @Column(name="Open")
    Map<String, Float> open = new HashMap<>();
    @Column(name="Close")
    Map<String, Float> close = new HashMap<>();
    @Column(name="High")
    Map<String, Float> high = new HashMap<>();
    @Column(name="Low")
    Map<String, Float> low = new HashMap<>();
    @Column(name = "Volume")
    Map<String, Float> volume = new HashMap<>();
}
