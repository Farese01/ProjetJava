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
    private Float open;

    @Column(name = "high_price")
    private Float high;

    @Column(name = "low_price")
    private Float low;

    @Column(name = "close_price")
    private Float close;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "count")
    private int count;

    public DailyStockPrice(StockEntity stock, LocalDate date, Float open, Float high, Float low, Float close, Long volume) {
        this.stock = stock;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.count = 0;
    }

    public DailyStockPrice(StockEntity stock, LocalDate date, Float open, Float high, Float low, Float close, Long volume, int count) {
        this.stock = stock;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.count = count;
    }
}

