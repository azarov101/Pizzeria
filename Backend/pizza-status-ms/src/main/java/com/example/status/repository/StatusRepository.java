package com.example.status.repository;

import com.example.status.model.StatusDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends MongoRepository<StatusDto, String> {
    Optional<StatusDto> findStatusDtoByOrderId(String orderId);
}
