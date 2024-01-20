package com.example.Projet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
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

    @Column(name="Symbol")
    private String symbol;

    private LocalDate lastRefreshed;

    private Integer count;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<DailyStockPrice> dailyPrices;


}

