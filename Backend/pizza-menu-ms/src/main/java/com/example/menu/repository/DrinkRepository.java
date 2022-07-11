package com.example.menu.repository;

import com.example.menu.dto.DrinkDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends MongoRepository<DrinkDto, String> {
}
