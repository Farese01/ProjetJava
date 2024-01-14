package com.example.Projet.controller;
import com.example.Projet.entity.StockValues;
import com.example.Projet.service.StockService;
import lombok.AllArgsConstructor;
import com.example.Projet.domain.Stock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("stock")
@AllArgsConstructor
public class StockController {
    private final StockService stockService;
    @GetMapping()
    public List<Stock> getAll() {
        return stockService.findAll();
    }

    @GetMapping("/symbol/{symbol}/date/{date}")
    public ResponseEntity<StockValues> getStockValuesBySymbolAndDate(@PathVariable String symbol,
                                                                     @PathVariable String date) {
        Optional<StockValues> stockValues = stockService.findStockValuesBySymbolAndDate(symbol, date);
        return stockValues.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
}
