package com.example.menu.mapper;

import com.example.menu.dto.DrinkDto;
import org.mapstruct.Mapper;
import org.openapitools.model.ExtraItem;

import java.util.List;

@Mapper
public interface DrinkMapper {
    List<ExtraItem> sourceToDestination(List<DrinkDto> source);
}
