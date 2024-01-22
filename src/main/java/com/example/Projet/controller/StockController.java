package com.example.Projet.controller;
import com.example.Projet.domain.StockOverview;
import com.example.Projet.domain.StockPriceDTO;
import com.example.Projet.domain.StockValues;
import com.example.Projet.service.StockService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("stock")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4200/getpricebydate"})

public class StockController {
    private final StockService stockService;

    @GetMapping()
    public List<StockOverview> getAll() {
        return stockService.findAll();
    }

    @GetMapping("/price")
    public Optional<StockPriceDTO> getStockPriceByDate(@RequestParam(name = "symbol") String symbol,
                                                       @RequestParam(name = "date") String date)  {

        StockPriceDTO stockPriceDTO = stockService.getStockPriceByDate(symbol, date);
        return Optional.ofNullable(stockPriceDTO);
    }

    @Data
    public static class StockPriceRequest {
        private String symbol;
        private String date;
    }

    @GetMapping("/prices-between-dates")
    public List<StockPriceDTO> getStockPricesBetweenDates(@RequestParam(name = "symbol") String symbol,
                                                          @RequestParam(name = "dateFrom") String dateFrom,
                                                          @RequestParam(name = "dateTo") String dateTo) {

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
    }

    @PostMapping("/create")
    public void postStock(@RequestBody StockCreated request) {
        stockService.fetchDataAndSave(request.getSymbol());

    }

    @Data
    public static class StockCreated {
        private String symbol;
    }

    @GetMapping("/nextday/{symbol}")
    public String suggestNextDayTrend(@PathVariable String symbol) {
        return stockService.suggestNextDayTrend(symbol);
    }
}


