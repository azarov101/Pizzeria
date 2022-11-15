package com.example.status.controller;

import com.example.status.service.StatusService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.GetOrderStatusApi;
import org.openapitools.model.GetOrderStatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "${cross.origins}")
@RequestMapping("status")
@RequiredArgsConstructor
@RestController
public class StatusController implements GetOrderStatusApi {

    private final StatusService statusService;

    @Override
    @GetMapping(value = "/getOrderStatus/{orderId}", produces = {"application/json"})
    public ResponseEntity<GetOrderStatusResponse> getOrderStatus(
            @Parameter(name = "orderId", description = "This number is the id of the order.", required = true) @PathVariable("orderId") String orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(statusService.getOrderStatus(orderId));
    }
}
