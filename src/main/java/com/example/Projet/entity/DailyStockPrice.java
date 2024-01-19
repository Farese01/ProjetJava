package com.example.Projet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.*;
@Entity
@Table(name = "daily_stock_prices")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyStockPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private StockEntity stock;

    private LocalDate date;

    @Column(name = "open_price")
    private Double open;

    @Column(name = "high_price")
    private Double high;

    @Column(name = "low_price")
    private Double low;

    @Column(name = "close_price")
    private Double close;

    private Long volume;

    // Constructors, getters, and setters...

    public DailyStockPrice(StockEntity stock, LocalDate date, Double open, Double high, Double low, Double close, Long volume) {
        this.stock = stock;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    // Constructors, getters, and setters...
}

