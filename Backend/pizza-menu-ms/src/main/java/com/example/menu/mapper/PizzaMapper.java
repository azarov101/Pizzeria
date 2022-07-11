package com.example.menu.mapper;

import com.example.menu.dto.PizzaDto;
import org.mapstruct.Mapper;
import org.openapitools.model.PizzaItem;

import java.util.List;

@Mapper
public interface PizzaMapper {
    List<PizzaItem> sourceToDestination(List<PizzaDto> source);
}
