package com.example.status.service.impl;

import com.example.status.mapper.StatusMapper;
import com.example.status.model.StatusDto;
import com.example.status.repository.StatusRepository;
import com.example.status.service.StatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.GetOrderStatusResponse;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Slf4j
@AllArgsConstructor
@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;

    @Override
    public GetOrderStatusResponse getOrderStatus(String orderId) {
        StatusDto statusDto = statusRepository.findStatusDtoByOrderId(orderId)
                .orElseThrow(() -> new NotFoundException(String.format("Order %s was not found", orderId)));

        return statusMapper.dtoToResponse(statusDto);
    }

    @Override
    public void updateOrderStatus(StatusDto statusDto) {
        StatusDto statusDtoToUpdate = statusRepository.findStatusDtoByOrderId(statusDto.getOrderId())
                .map(statusDto1 -> {
                    statusDto1.setStatus(statusDto.getStatus());
                    return statusDto1;
                })
                .orElse(statusDto);

        statusRepository.save(statusDtoToUpdate);
    }
}
