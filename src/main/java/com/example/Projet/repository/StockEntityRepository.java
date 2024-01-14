package com.example.Projet.repository;

import com.example.Projet.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.Optional;
import java.util.List;
public interface StockEntityRepository extends JpaRepository<StockEntity, UUID>{
    List <StockEntity> findAll();
    Optional <StockEntity> findBySymbol(String symbol);

}
