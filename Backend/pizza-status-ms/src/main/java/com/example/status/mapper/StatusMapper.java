package com.example.status.mapper;

import com.example.status.model.OrderStatusMessage;
import com.example.status.model.StatusDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.GetOrderStatusResponse;

@Mapper
public interface StatusMapper {

    GetOrderStatusResponse dtoToResponse(StatusDto statusDto);

    @Mapping(target = "id", ignore = true)
    StatusDto messageToDto(OrderStatusMessage orderStatusMessage);
}
