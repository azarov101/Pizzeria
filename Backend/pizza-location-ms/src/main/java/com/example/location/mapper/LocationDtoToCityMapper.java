package com.example.location.mapper;

import com.example.location.dto.LocationDto;
import org.mapstruct.Mapper;
import org.openapitools.model.City;

import java.util.List;

@Mapper
public interface LocationDtoToCityMapper {
    List<City> sourceToDestination(List<LocationDto> source);
}
