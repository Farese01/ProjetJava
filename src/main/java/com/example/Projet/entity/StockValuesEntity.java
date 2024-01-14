package com.example.Projet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="stock_values")
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class StockValuesEntity {
    private Float open;
    private Float close;
    private Float high;
    private Float low;
    private Float volume;
    private Integer count;


}
