package com.example.menu.resources.rest;

import com.example.menu.resources.controller.MenuController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzamenu.api.external.GetMenuApi;
import com.example.pizzamenu.model.external.GetMenuResponse;

@RestController
@CrossOrigin(origins = "${cros.origins}")
@RequestMapping("menu")
public class MenuRest implements GetMenuApi {

	protected MenuController menuController;
	
	public MenuRest(MenuController menuController) {
		this.menuController = menuController;
	}
	
	@Override
	public ResponseEntity<GetMenuResponse> getMenuGet() {
		return new ResponseEntity<>(menuController.getMenu(), HttpStatus.OK);
	}
}
