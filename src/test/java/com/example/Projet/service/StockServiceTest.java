package com.example.Projet.service;

import com.example.Projet.entity.DailyStockPrice;
import com.example.Projet.entity.StockEntity;
import com.example.Projet.domain.StockPriceDTO;
import com.example.Projet.repository.StockEntityRepository;
import com.example.Projet.service.impl.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        String dateTo = "2022-01-20";

        StockEntity stockEntity = createMockStockEntity(symbol);
        when(stockEntityRepository.findBySymbol(symbol)).thenReturn(Optional.of(stockEntity));

        // Perform the test
        List<StockPriceDTO> result = stockService.getStockPricesBetweenDates(symbol, dateFrom, dateTo);

        // Verify the result
        assertNotNull(result);
        assertFalse(result.isEmpty());
        // Add more assertions based on your implementation

        // Verify that the count is incremented for each date
        for (LocalDate currentDate = LocalDate.parse(dateFrom); currentDate.isBefore(LocalDate.parse(dateTo).plusDays(1)); currentDate = currentDate.plusDays(1)) {
            LocalDate finalCurrentDate = currentDate;
            Optional<DailyStockPrice> dailyPriceOptional = stockEntity.getDailyPrices().stream()
                    .filter(dailyStockPrice -> dailyStockPrice.getDate().equals(LocalDate.parse(finalCurrentDate.toString())))
                    .findFirst();
            assertTrue(dailyPriceOptional.isPresent());
            assertEquals(1, dailyPriceOptional.get().getCount());
        }
    }
    @Test
    void testGetStockPriceByDate() {
        // Mock data
        String symbol = "AAPL";
        String targetDate = "2022-01-15";

        StockEntity stockEntity = createMockStockEntity(symbol);
        when(stockEntityRepository.findBySymbol(symbol)).thenReturn(Optional.of(stockEntity));

        // Perform the test
        StockPriceDTO result = stockService.getStockPriceByDate(symbol, targetDate);

        // Verify the result
        assertNotNull(result);
        assertEquals(symbol, result.getSymbol());
        assertEquals(targetDate, result.getDate());
        // Add more assertions based on your implementation

        // Verify that the count is incremented
        Optional<DailyStockPrice> dailyPriceOptional = stockEntity.getDailyPrices().stream()
                .filter(dailyStockPrice -> dailyStockPrice.getDate().equals(LocalDate.parse(targetDate)))
                .findFirst();
        assertTrue(dailyPriceOptional.isPresent());
        assertEquals(1, dailyPriceOptional.get().getCount());
    }

    @Test
    void testGetStockPriceByDateNotFound() {
        // Mock data
        String symbol = "AAPL";
        String targetDate = "2022-01-18";

        StockEntity stockEntity = createMockStockEntity(symbol);
        when(stockEntityRepository.findBySymbol(symbol)).thenReturn(Optional.of(stockEntity));

        // Perform the test
        StockPriceDTO result = stockService.getStockPriceByDate(symbol, targetDate);

        // Verify the result
        assertNull(result);

        // Verify that the count is not incremented for a non-existent date
        Optional<DailyStockPrice> dailyPriceOptional = stockEntity.getDailyPrices().stream()
                .filter(dailyStockPrice -> dailyStockPrice.getDate().equals(LocalDate.parse(targetDate)))
                .findFirst();
        assertFalse(dailyPriceOptional.isPresent());
    }

    @Test
    void testFindMostSearchedStock() {
        // Mock data
        StockEntity stockEntity1 = createMockStockEntity("AAPL");
        StockEntity stockEntity2 = createMockStockEntity("GOOGL");
        List<StockEntity> stockEntities = List.of(stockEntity1, stockEntity2);

        when(stockEntityRepository.findAll()).thenReturn(stockEntities);

        // Perform the test
        Map.Entry<String, Float> result = stockService.findMostSearchedStock();

        // Verify the result
        assertNotNull(result);
        assertEquals("AAPL", result.getKey());
        // Add more assertions based on your implementation
    }

    @Test
    void testFindMostSearchedStockEmptyList() {
        // Mock an empty list of stock entities
        when(stockEntityRepository.findAll()).thenReturn(new ArrayList<>());

        // Perform the test
        Map.Entry<String, Float> result = stockService.findMostSearchedStock();

        // Verify the result
        assertNull(result);
    }
    private StockEntity createMockStockEntity(String symbol) {
        StockEntity stockEntity = new StockEntity();
        stockEntity.setSymbol(symbol);

        DailyStockPrice dailyStockPrice1 = new DailyStockPrice();
        dailyStockPrice1.setDate(LocalDate.parse("2022-01-15"));
        dailyStockPrice1.setOpen(150.0f);
        dailyStockPrice1.setClose(155.0f);
        dailyStockPrice1.setLow(148.0f);
        dailyStockPrice1.setHigh(160.0f);
        dailyStockPrice1.setVolume(1000L);
        dailyStockPrice1.setCount(0);

        DailyStockPrice dailyStockPrice2 = new DailyStockPrice();
        dailyStockPrice2.setDate(LocalDate.parse("2022-01-16"));
        dailyStockPrice2.setOpen(160.0f);
        dailyStockPrice2.setClose(165.0f);
        dailyStockPrice2.setLow(158.0f);
        dailyStockPrice2.setHigh(170.0f);
        dailyStockPrice2.setVolume(1200L);
        dailyStockPrice2.setCount(0);

        stockEntity.setDailyPrices(List.of(dailyStockPrice1, dailyStockPrice2));

        return stockEntity;
    }
}
