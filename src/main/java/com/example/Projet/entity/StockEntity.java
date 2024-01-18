package com.example.Projet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

@Entity
@Table(name = "stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Symbol")
    private String symbol;

    @ElementCollection
    @MapKeyColumn(name = "date")  // column name for map keys
    @Column(name = "value")      // column name for map values
    @CollectionTable(name = "stock_open", joinColumns = @JoinColumn(name = "stock_id"))
    private Map<String, Float> open = new HashMap<>();

    @ElementCollection
    @MapKeyColumn(name = "date")
    @Column(name = "value")
    @CollectionTable(name = "stock_close", joinColumns = @JoinColumn(name = "stock_id"))
    private Map<String, Float> close = new HashMap<>();

    @ElementCollection
    @MapKeyColumn(name = "date")
    @Column(name = "value")
    @CollectionTable(name = "stock_high", joinColumns = @JoinColumn(name = "stock_id"))
    private Map<String, Float> high = new HashMap<>();

    @ElementCollection
    @MapKeyColumn(name = "date")
    @Column(name = "value")
    @CollectionTable(name = "stock_low", joinColumns = @JoinColumn(name = "stock_id"))
    private Map<String, Float> low = new HashMap<>();

    @ElementCollection
    @MapKeyColumn(name = "date")
    @Column(name = "value")
    @CollectionTable(name = "stock_volume", joinColumns = @JoinColumn(name = "stock_id"))
    private Map<String, Float> volume = new HashMap<>();

    @ElementCollection
    @MapKeyColumn(name = "date")
    @Column(name = "value")
    @CollectionTable(name = "stock_count", joinColumns = @JoinColumn(name = "stock_id"))
    private Map<String, Float> count = new HashMap<>();

    // Constructors, getters, setters...
}