package com.example.delivery.persistence.dao;

import com.example.order.model.external.DeliveryDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeliveryDAO {

    protected String collectionName;
    protected MongoTemplate mongoTemplate;

    protected static Logger logger = LoggerFactory.getLogger(DeliveryDAO.class);

    public DeliveryDAO(@Value("${spring.data.mongodb.collection-name}")String collectionName, MongoTemplate mongoTemplate) {
        this.collectionName = collectionName;
        this.mongoTemplate = mongoTemplate;
    }

    public DeliveryDetailsResponse getDeliveryDetails(Long orderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where(orderId.toString()).is(orderId));
        DeliveryDetailsResponse response;

        try {
            logger.debug("Trying to retrieve order details for {} from Database", orderId.toString());
            response = mongoTemplate.findOne(query, DeliveryDetailsResponse.class, collectionName);

            if (response == null) {
                throw new Exception("There are no details for order:" + orderId.toString() + " in Database");
            }
            logger.debug("Successfully retrieved details for order {}", orderId.toString());

        } catch (Exception e) {
            logger.error("Database error when trying to retrieve order details. Error Message: {}", e.getMessage());
            response = new DeliveryDetailsResponse();
        }

        return response;
    }
}
