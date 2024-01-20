package com.example.Projet;


import com.example.Projet.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Projet.entity.StockEntity;
import com.example.Projet.repository.StockEntityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@SpringBootApplication
public class ProjetApplication {
	@Autowired
	private StockService stockService;
	public static void main(String[] args) {
		SpringApplication.run(ProjetApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(StockEntityRepository stockRepository) {
		return (args) -> {
			stockService.fetchDataAndSave("KO");
			System.out.println("Stock data fetched and saved successfully!");
		};
	}


}
