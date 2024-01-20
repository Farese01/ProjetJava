package com.example.Projet;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.example.Projet.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Projet.repository.StockEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


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
			stockService.fetchDataAndSave("IBM");
			System.out.println("Stock data fetched and saved successfully!");
		};
	}


}
