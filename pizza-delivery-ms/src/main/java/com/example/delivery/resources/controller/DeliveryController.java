package com.example.delivery.resources.controller;

import com.example.delivery.persistence.dao.DeliveryDAO;
import com.example.order.model.external.DeliveriesResponse;
import com.example.order.model.external.Delivery;
import com.example.order.model.external.DeliveryDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
public class DeliveryController {

    protected DeliveryDAO deliveryDAO;

    protected static Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    public DeliveryController(DeliveryDAO deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    public DeliveryDetailsResponse getDeliveryDetails(Long orderId) {
        return deliveryDAO.getDeliveryDetails(orderId);
    }

    public DeliveriesResponse getDeliveries() {
        List<Delivery> deliveryList = deliveryDAO.getDeliveries();

        DeliveriesResponse response = new DeliveriesResponse();
        response.setDeliveryList(deliveryList);

        return response;
    }
}
