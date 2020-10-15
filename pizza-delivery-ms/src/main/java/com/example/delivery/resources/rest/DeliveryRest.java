package com.example.delivery.resources.rest;

import com.example.delivery.resources.controller.DeliveryController;
import com.example.order.api.external.GetDeliveriesApi;
import com.example.order.api.external.GetDeliveryDetailsApi;
import com.example.order.model.external.DeliveriesResponse;
import com.example.order.model.external.DeliveryDetailsRequest;
import com.example.order.model.external.DeliveryDetailsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "${cross.origins}")
public class DeliveryRest implements GetDeliveryDetailsApi, GetDeliveriesApi {

    protected DeliveryController deliveryController;

    public DeliveryRest(DeliveryController deliveryController) {
        this.deliveryController = deliveryController;
    }

    @Override
    public ResponseEntity<DeliveryDetailsResponse> getDeliveryDetails(@Valid DeliveryDetailsRequest deliveryDetailsRequest) {
        return new ResponseEntity<>(deliveryController.getDeliveryDetails(deliveryDetailsRequest.getOrderId()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeliveriesResponse> getDeliveries() {
        return new ResponseEntity<>(deliveryController.getDeliveries(), HttpStatus.OK);
    }
}
