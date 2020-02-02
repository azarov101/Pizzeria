package com.amdocs.location.resources.services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.amdocs.location.resources.services.interfaces.ILocationService;
import com.example.pizzalocation.model.external.City;
import com.example.pizzalocation.model.external.GetCityListResponse;

@Service
public class LocationService implements ILocationService {

	protected MongoTemplate mongoTemplate;
	
	public LocationService(MongoTemplate mongoTemplate) {
		super();
		this.mongoTemplate = mongoTemplate;
	}
	
	public GetCityListResponse getCityList() {
		List<City> cityList = mongoTemplate.findAll(City.class, "pizza_location");
		GetCityListResponse response = new GetCityListResponse();
		response.setCityList(cityList);
		return response;
	}
	
}
