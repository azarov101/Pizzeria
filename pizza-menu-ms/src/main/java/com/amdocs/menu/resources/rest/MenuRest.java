package com.amdocs.menu.resources.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.menu.resources.services.interfaces.IMenuService;
import com.example.pizzamenu.api.external.GetMenuApi;
import com.example.pizzamenu.model.external.GetMenuResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("menu")
public class MenuRest implements GetMenuApi {

	protected IMenuService menuService;
	
	public MenuRest(IMenuService menuService) {
		super();
		this.menuService = menuService;
	}
	
	@Override
	@GetMapping("/getMenu")
	public ResponseEntity<GetMenuResponse> getMenuGet() {
		return new ResponseEntity<>(menuService.getMenu(), HttpStatus.OK);
	}

}
