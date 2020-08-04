package com.example.location.resources.rest;

import com.example.location.resources.controller.LocationController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzalocation.api.external.GetCityListApi;
import com.example.pizzalocation.model.external.GetCityListResponse;

@RestController
@CrossOrigin(origins = "${cros.origins}")
@RequestMapping("location")
public class LocationRest implements GetCityListApi {
	
	protected LocationController locationController;
	
	public LocationRest(LocationController locationController) {
		this.locationController = locationController;
	}

	@Override
	public ResponseEntity<GetCityListResponse> getCityListGet() {
		return new ResponseEntity<>(locationController.getCityList(), HttpStatus.OK);
	}
}
