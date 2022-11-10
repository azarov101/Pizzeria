package com.example.order.repository;

import com.example.order.dto.OrderDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderDto, String> {
}
