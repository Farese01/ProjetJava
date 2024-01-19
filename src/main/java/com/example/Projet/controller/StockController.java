package com.example.Projet.controller;
import com.example.Projet.entity.StockValues;
import com.example.Projet.service.StockService;
import lombok.AllArgsConstructor;
import com.example.Projet.domain.Stock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
<<<<<<< Updated upstream

    @GetMapping("/symbol/{symbol}/date/{date}")
    public ResponseEntity<StockValues> getStockValuesBySymbolAndDate(@PathVariable String symbol,
                                                                     @PathVariable String date) {
        Optional<StockValues> stockValues = stockService.findStockValuesBySymbolAndDate(symbol, date);
        return stockValues.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
=======
    /*@PostMapping("/price")
    public Optional<StockPriceDTO> getStockPriceBySymbolAndDate(@RequestBody StockPriceRequest request) {
        String symbol = request.getSymbol();
        LocalDate date = request.getDate();
        StockPriceDTO stockPriceDTO = stockService.getStockPriceByDate(symbol, date);
        return Optional.ofNullable(stockPriceDTO);
    }

    @Data
    public static class StockPriceRequest {
        private String symbol;
        private LocalDate date;
    }

    @GetMapping("/prices-between-dates")
    public List<StockPriceDTO> getStockPricesBetweenDates(@RequestBody StockPriceRequestDates request) {
        String symbol = request.getSymbol();
        String dateFrom = request.getDateFrom();
        String dateTo = request.getDateTo();
        return stockService.getStockPricesBetweenDates(symbol, dateFrom, dateTo);
    }
    @Data
    public static class StockPriceRequestDates {
        private String symbol;
        private String dateFrom;
        private String dateTo;
    }

    @GetMapping("/most-searched")
    public Optional<Map.Entry<String, Float>> getMostSearchedStock() {
        return Optional.ofNullable(stockService.findMostSearchedStock());
    }*/
>>>>>>> Stashed changes
}
