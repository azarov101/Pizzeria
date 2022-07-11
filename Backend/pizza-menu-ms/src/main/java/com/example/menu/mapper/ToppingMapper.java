package com.example.menu.mapper;

import com.example.menu.dto.ToppingDto;
import org.mapstruct.Mapper;
import org.openapitools.model.ExtraItem;

import java.util.List;

@Mapper
public interface ToppingMapper {
    List<ExtraItem> sourceToDestination(List<ToppingDto> source);
}
