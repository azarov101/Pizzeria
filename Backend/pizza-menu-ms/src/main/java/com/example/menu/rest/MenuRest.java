package com.example.menu.rest;

import com.example.menu.controller.MenuController;
import org.openapitools.api.GetMenuApi;
import org.openapitools.model.GetMenuResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "${cross.origins}")
@RequestMapping("menu")
public class MenuRest implements GetMenuApi {

	protected MenuController menuController;

	public MenuRest(MenuController menuController) {
		this.menuController = menuController;
	}

	@Override
	@GetMapping(value = "/getMenu",produces = { "application/json" })
	public ResponseEntity<GetMenuResponse> getMenu() {
		return new ResponseEntity<>(menuController.getMenu(), HttpStatus.OK);
	}
}
