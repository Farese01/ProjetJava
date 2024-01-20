package com.example.Projet.service;

import com.example.Projet.entity.DailyStockPrice;
import com.example.Projet.entity.StockEntity;
import com.example.Projet.domain.StockPriceDTO;
import com.example.Projet.repository.StockEntityRepository;
import com.example.Projet.service.impl.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Mock
    private StockEntityRepository stockEntityRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    void setUp() {
        stockService = new StockServiceImpl(stockEntityRepository);
    }

    @Test
    void testGetStockPricesBetweenDates() {
        // Mock data
        String symbol = "AAPL";
        String dateFrom = "2022-01-15";
        String dateTo = "2022-01-16";

        StockEntity stockEntity = createMockStockEntity(symbol);
        when(stockEntityRepository.findBySymbol(symbol)).thenReturn(Optional.of(stockEntity));

        List<StockPriceDTO> result = stockService.getStockPricesBetweenDates(symbol, dateFrom, dateTo);

        assertNotNull(result);
        assertFalse(result.isEmpty());

        for (LocalDate currentDate = LocalDate.parse(dateFrom); currentDate.isBefore(LocalDate.parse(dateTo).plusDays(1)); currentDate = currentDate.plusDays(1)) {
            LocalDate finalCurrentDate = currentDate;
            Optional<DailyStockPrice> dailyPriceOptional = stockEntity.getDailyPrices().stream()
                    .filter(dailyStockPrice -> dailyStockPrice.getDate().equals(LocalDate.parse(finalCurrentDate.toString())))
                    .findFirst();
            assertTrue(dailyPriceOptional.isPresent());
        }
    }
    @Test
    void testGetStockPriceByDate() {

        String symbol = "AAPL";
        String targetDate = "2022-01-15";

        StockEntity stockEntity = createMockStockEntity(symbol);
        when(stockEntityRepository.findBySymbol(symbol)).thenReturn(Optional.of(stockEntity));

        StockPriceDTO result = stockService.getStockPriceByDate(symbol, targetDate);

        assertNotNull(result);
        assertEquals(symbol, result.getSymbol());
        assertEquals(targetDate, result.getDate());
        assertEquals(150.0f, result.getOpenValue(), 0.001f); // Assert open price
        assertEquals(155.0f, result.getCloseValue(), 0.001f); // Assert close price
        assertEquals(160.0f, result.getHighValue(), 0.001f); // Assert high price
        assertEquals(148.0f, result.getLowValue(), 0.001f);  // Assert low price

        Optional<DailyStockPrice> dailyPriceOptional = stockEntity.getDailyPrices().stream()
                .filter(dailyStockPrice -> dailyStockPrice.getDate().equals(LocalDate.parse(targetDate)))
                .findFirst();
        assertTrue(dailyPriceOptional.isPresent());
    }

    @Test
    void testGetStockPriceByDateNotFound() {

        String symbol = "AAPL";
        String targetDate = "2022-01-18";

        StockEntity stockEntity = createMockStockEntity(symbol);
        when(stockEntityRepository.findBySymbol(symbol)).thenReturn(Optional.of(stockEntity));

        StockPriceDTO result = stockService.getStockPriceByDate(symbol, targetDate);

        assertNull(result);

        Optional<DailyStockPrice> dailyPriceOptional = stockEntity.getDailyPrices().stream()
                .filter(dailyStockPrice -> dailyStockPrice.getDate().equals(LocalDate.parse(targetDate)))
                .findFirst();
        assertFalse(dailyPriceOptional.isPresent());
    }

    @Test
    void testFindMostSearchedStock() {

        StockEntity stockEntity1 = createMockStockEntity("AAPL");
        StockEntity stockEntity2 = createMockStockEntity("GOOGL");
        List<StockEntity> stockEntities = List.of(stockEntity1, stockEntity2);

        when(stockEntityRepository.findAll()).thenReturn(stockEntities);

        Map.Entry<String, Float> result = stockService.findMostSearchedStock();

        assertNotNull(result);
        assertEquals("AAPL", result.getKey());
    }

    @Test
    void testFindMostSearchedStockEmptyList() {

        when(stockEntityRepository.findAll()).thenReturn(new ArrayList<>());

        Map.Entry<String, Float> result = stockService.findMostSearchedStock();

        assertNull(result);
    }


    @Test
    void testSuggestNextDayTrend() {
        String symbol = "AAPL";

        StockEntity stockEntity = createMockStockEntity(symbol);
        when(stockEntityRepository.findBySymbol(symbol)).thenReturn(Optional.of(stockEntity));

        String result = stockService.suggestNextDayTrend(symbol);

        assertNotNull(result);
        assertTrue(result.contains("Suggesting:"));
    }

    @Test
    void testSuggestNextDayTrendNoData() {
        String symbol = "AAPL";

        when(stockEntityRepository.findBySymbol(symbol)).thenReturn(Optional.empty());

        String result = stockService.suggestNextDayTrend(symbol);

        assertEquals("Unable to provide a suggestion.", result);
    }

    @Test
    void testSuggestNextDayTrendInsufficientData() {
        String symbol = "AAPL";

        StockEntity stockEntity = createMockStockEntity(symbol);
        stockEntity.setDailyPrices(Collections.emptyList()); // Simulate insufficient data
        when(stockEntityRepository.findBySymbol(symbol)).thenReturn(Optional.of(stockEntity));

        String result = stockService.suggestNextDayTrend(symbol);

        assertEquals("Insufficient data to provide a suggestion.", result);
    }


    private StockEntity createMockStockEntity(String symbol) {
        StockEntity stockEntity = new StockEntity();
        stockEntity.setSymbol(symbol);
        Random random = new Random();

        int count = symbol.equals("AAPL") ? 100 : random.nextInt(6);

        DailyStockPrice dailyStockPrice1 = new DailyStockPrice(stockEntity, LocalDate.parse("2022-01-15"), 150.0f, 160.0f, 148.0f, 155.0f, 1000L, count);
        DailyStockPrice dailyStockPrice2 = new DailyStockPrice(stockEntity, LocalDate.parse("2022-01-16"), 160.0f, 170.0f, 158.0f, 165.0f, 1200L, count);

        stockEntity.setDailyPrices(List.of(dailyStockPrice1, dailyStockPrice2));

        return stockEntity;
    }

}
