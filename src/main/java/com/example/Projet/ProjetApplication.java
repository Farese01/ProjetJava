package com.example.Projet;

<<<<<<< Updated upstream
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
=======
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
>>>>>>> Stashed changes

@SpringBootApplication
public class ProjetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetApplication.class, args);
	}

<<<<<<< Updated upstream
=======
	/*@Bean
	public CommandLineRunner demo(StockEntityRepository stockRepository) {
		return (args) -> {
			RestTemplate restTemplate = new RestTemplate();
			System.out.println(url("IBM"));
			String jsonString = restTemplate.getForObject(url("IBM"), String.class);
			List<StockEntity> stockList = mapJsonToEntities(jsonString);
			stockRepository.saveAll(stockList);

		};
	}*/

>>>>>>> Stashed changes
}
