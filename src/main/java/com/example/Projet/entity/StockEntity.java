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
<<<<<<< Updated upstream
    @Column(name="Symbol")
=======

>>>>>>> Stashed changes
    private String symbol;
    @Column(name="Stock Values")
    @OneToMany(cascade = CascadeType.ALL)
    public Map<String, StockValuesEntity> getStockValuesMap=new HashMap<>();
    
}

<<<<<<< Updated upstream

  //  @Column(name="Open")
  //   Map<String, Float> open = new HashMap<>();
  //   @Column(name="Close")
  //Map<String, Float> close = new HashMap<>();
  //@Column(name="High")
  //Map<String, Float> high = new HashMap<>();
  ////@Column(name="Low")
  //Map<String, Float> low = new HashMap<>();
  //@Column(name = "Volume")
  //Map<String, Float> volume = new HashMap<>();
  //@Column(name = "Count")
    //Map<String, Float> count = new HashMap<>();

}
=======
    private LocalDate lastRefreshed;

    private Float count;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<DailyStockPrice> dailyPrices;

    // Constructors, getters, and setters...

}
>>>>>>> Stashed changes
