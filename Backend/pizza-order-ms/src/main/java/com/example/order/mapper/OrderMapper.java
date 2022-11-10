package com.example.order.mapper;

import com.example.order.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.CreateOrderRequest;
import org.openapitools.model.CreateOrderResponse;
import org.openapitools.model.GetOrderDetailsResponse;

import java.util.List;

@Mapper(uses = {OrderDetailsMapper.class})
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    OrderDto requestToDto(CreateOrderRequest orderRequest);

    List<GetOrderDetailsResponse> dtoToResponse(List<OrderDto> orderDtos);

    @Mapping(target = "orderId", source = "id")
    GetOrderDetailsResponse dtoToResponse(OrderDto orderDto);

//    @Mapping(target = "requestStatus", source = "requestStatus")
    @Mapping(target = "orderId", source = "orderDto.id")
    CreateOrderResponse dtoToOrderResponse(OrderDto orderDto, String requestStatus);
}
