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
    @Column(name = "Count")
    Map<String, Float> count = new HashMap<>();

}
