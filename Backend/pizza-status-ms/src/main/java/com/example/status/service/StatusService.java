package com.example.status.service;


import com.example.status.model.StatusDto;
import org.openapitools.model.GetOrderStatusResponse;

public interface StatusService {
    GetOrderStatusResponse getOrderStatus(String orderId);

    void updateOrderStatus(StatusDto statusDto);
}
