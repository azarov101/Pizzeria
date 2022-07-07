package com.example.delivery.persistence.dao;

import com.example.delivery.persistence.dto.DeliveryDTO;
import com.example.delivery.utils.Constants;
import com.example.delivery.utils.exceptions.InternalServerException;
import com.example.delivery.utils.exceptions.NotFoundException;
import com.example.order.model.external.Delivery;
import com.example.order.model.external.DeliveryDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.delivery.utils.Constants.database_error;

@Slf4j
@Service
public class DeliveryDAO {

    protected String collectionName;
    protected MongoTemplate mongoTemplate;

    protected static Logger logger = LoggerFactory.getLogger(DeliveryDAO.class);

    public DeliveryDAO(@Value("${spring.data.mongodb.collection-name}") String collectionName, MongoTemplate mongoTemplate) {
        this.collectionName = collectionName;
        this.mongoTemplate = mongoTemplate;
    }

    public DeliveryDetailsResponse getDeliveryDetails(Long orderId) {
        Query query = getDeliveryById(orderId);
        logger.debug("Trying to retrieve delivery details for {} from Database", orderId.toString());

        try {
            DeliveryDetailsResponse response = mongoTemplate.findOne(query, DeliveryDetailsResponse.class, collectionName);

            if (response == null) {
                throw new NotFoundException("There are no details for order:" + orderId.toString() + " in Database");
            }

            logger.debug("Retrieved details for order {}", orderId.toString());
            return response;

        } catch (NotFoundException e) {
            logger.error("{}", e.getMessage());
            throw new NotFoundException(e.getMessage(), e);
        } catch (Exception e) {
            String error = database_error("delivery details");
            logger.error(error + ". Error Message: {}", e.getMessage());
            throw new InternalServerException(error, e);
        }
    }

    public List<Delivery> getDeliveries() {
        List<Delivery> deliveryList = null;

        try {
            logger.debug("Trying to retrieve all deliveries from Database");
            deliveryList = mongoTemplate.findAll(Delivery.class, collectionName);

            if (deliveryList == null) {
                throw new NotFoundException("There are no deliveries in Database");
            }
            logger.debug("Successfully retrieved deliveries");

        } catch (NotFoundException e) {
            logger.error("{}", e.getMessage());
            throw new NotFoundException(e.getMessage(), e);
        } catch (Exception e) {
            String error = database_error("deliveries");
            logger.error(error + ". Error Message: {}", e.getMessage());
            throw new InternalServerException(error, e);
        }

        return deliveryList;
    }

    public void saveDelivery(DeliveryDTO deliveryDTO) {
        logger.debug("Trying to save delivery {} to Database", deliveryDTO.getOrderId());

        try {
            mongoTemplate.save(deliveryDTO);
            logger.debug("Successfully saved delivery");

        } catch (Exception e) {
            logger.error("Database error when trying to save delivery. Error Message: {}", e.getMessage());
            throw e;
        }
    }

    public void updateDelivery(DeliveryDTO deliveryDTO) {
        logger.debug("Trying to update delivery {} in Database", deliveryDTO.getOrderId());
        Query query = getDeliveryById(deliveryDTO.getOrderId());

        Document doc = new Document();
        mongoTemplate.getConverter().write(deliveryDTO, doc);
        Update update = Update.fromDocument(doc);

        try {
            mongoTemplate.upsert(query, update, DeliveryDTO.class);
            logger.debug("Successfully updated delivery");

        } catch (Exception e) {
            logger.error("Database error when trying to update delivery. Error Message: {}", e.getMessage());
            throw e;
        }
    }

    private Query getDeliveryById(Long orderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Constants.ORDER_ID).is(orderId));
        return query;
    }
}
