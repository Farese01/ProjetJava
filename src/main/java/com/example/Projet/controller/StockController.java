package com.example.Projet.controller;
import com.example.Projet.domain.StockPriceDTO;
import com.example.Projet.domain.StockValues;
import com.example.Projet.entity.StockEntity;
import com.example.Projet.service.StockService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("stock")
@AllArgsConstructor
public class StockController {
    private final StockService stockService;
    @GetMapping()
    public List<StockValues> getAll() {
        return stockService.findAll();
    }

    @PostMapping("/price")
    public Optional<StockPriceDTO> getStockPriceBySymbolAndDate(@RequestBody StockPriceRequest request) {
        String symbol = request.getSymbol();
        String date = request.getDate();
        StockPriceDTO stockPriceDTO = stockService.getStockPriceByDate(symbol, date);
        return Optional.ofNullable(stockPriceDTO);
    }

    @Data
    public static class StockPriceRequest {
        private String symbol;
        private String date;
    }
    }
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


