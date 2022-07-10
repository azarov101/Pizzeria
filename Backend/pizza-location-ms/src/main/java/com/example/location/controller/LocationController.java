package com.example.location.controller;

import com.example.location.dto.LocationDto;
import com.example.location.mapper.LocationDtoToCityMapper;
import com.example.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.City;
import org.openapitools.model.GetCityListResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LocationController {

    private final LocationRepository locationRepository;
    private final LocationDtoToCityMapper locationDtoToCityMapper;

    public GetCityListResponse getCityList() {
        List<LocationDto> cityList;

        try {
            log.debug("Trying to retrieve cities from Database");
            cityList = locationRepository.findAll();

            if (CollectionUtils.isEmpty(cityList)) {
                throw new NotFoundException("There are no cities in Database");
            }
            log.debug("Successfully retrieved {} cities", cityList.size());

        } catch (Exception e) {
            log.error("Database error when trying to retrieve cities. Error Message: {}", e.getMessage());
            cityList = new ArrayList<>();
        }

        return createResponse(locationDtoToCityMapper.sourceToDestination(cityList));
    }

    private GetCityListResponse createResponse(List<City> cityList) {
        GetCityListResponse response = new GetCityListResponse();
        response.setCityList(cityList);
        return response;
    }
}
