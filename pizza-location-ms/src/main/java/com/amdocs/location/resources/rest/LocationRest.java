package com.amdocs.location.resources.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.location.resources.services.interfaces.ILocationService;
import com.example.pizzalocation.api.external.GetCityListApi;
import com.example.pizzalocation.model.external.GetCityListResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("location")
public class LocationRest implements GetCityListApi {
	
	protected ILocationService locationService;
	
	public LocationRest(ILocationService locationService) {
		super();
		this.locationService = locationService;
	}

	@Override
	@GetMapping("/getCityList")
	public ResponseEntity<GetCityListResponse> getCityListGet() {
		return new ResponseEntity<>(locationService.getCityList(), HttpStatus.OK);
	}

}
