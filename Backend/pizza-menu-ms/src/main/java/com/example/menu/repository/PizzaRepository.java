package com.example.menu.repository;

import com.example.menu.dto.PizzaDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends MongoRepository<PizzaDto, String> {
}
