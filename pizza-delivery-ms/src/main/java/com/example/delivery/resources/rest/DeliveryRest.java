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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "${cros.origins}")
@RequestMapping("delivery")
public class DeliveryRest implements GetDeliveryDetailsApi, GetDeliveriesApi {

    protected DeliveryController deliveryController;

    public DeliveryRest(DeliveryController deliveryController) {
        this.deliveryController = deliveryController;
    }

    @Override
    public ResponseEntity<DeliveryDetailsResponse> getDeliveryDetailsPost(DeliveryDetailsRequest deliveryDetailsRequest) {
        if (!isRequestValid(deliveryDetailsRequest)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(deliveryController.getDeliveryDetails(deliveryDetailsRequest.getOrderId()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeliveriesResponse> getDeliveriesGet() {
        return new ResponseEntity<>(deliveryController.getDeliveries(), HttpStatus.OK);
    }

    private boolean isRequestValid(DeliveryDetailsRequest deliveryDetailsRequest) {
        if (deliveryDetailsRequest == null || deliveryDetailsRequest.getOrderId() == null
                || deliveryDetailsRequest.getOrderId() <= 0) {
            return false;
        }
        return true;
    }
}
