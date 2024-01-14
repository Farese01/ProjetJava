package com.example.Projet.controller;
import com.example.Projet.service.StockService;
import lombok.AllArgsConstructor;
import com.example.Projet.domain.Stock;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
}
