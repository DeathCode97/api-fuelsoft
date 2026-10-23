package com.fuelsoft.apifuel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FuelController {
	
	@GetMapping("/prueba")
	public String prueba() {
		return "akisia";
	}
}
