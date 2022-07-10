package com.example.location.repository;

import com.example.location.dto.LocationDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends MongoRepository<LocationDto, String> {
}
