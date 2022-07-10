package com.example.location.rest;

import com.example.location.controller.LocationController;
import org.openapitools.api.GetCityListApi;
import org.openapitools.model.GetCityListResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "${cross.origins}")
@RequestMapping("location")
public class LocationRest implements GetCityListApi {

    protected LocationController locationController;

    public LocationRest(LocationController locationController) {
        this.locationController = locationController;
    }

    @Override
    @GetMapping(value = "/getCityList", produces = {"application/json"})
    public ResponseEntity<GetCityListResponse> getCityList() {
        return new ResponseEntity<>(locationController.getCityList(), HttpStatus.OK);
    }
}
