package com.example.menu.repository;

import com.example.menu.dto.ToppingDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends MongoRepository<ToppingDto, String> {
}
