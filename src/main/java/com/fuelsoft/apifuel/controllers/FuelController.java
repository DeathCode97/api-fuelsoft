package com.fuelsoft.apifuel.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

//colocar el sufijo en plural
@RequestMapping("/api/pruebas")
public class FuelController {

//	El pathvariable es el parametro que recibe mediante la url para actualizar o eliminar
	@GetMapping
	public String listarPruebas() {
		return "Regrese Lista de usuario xdxdxd";
	}
	
	@GetMapping("/{id}")
	public  String obtenerPruebaPorId(@PathVariable Long id) {
		return "El id es " + id;
	}
	
	@PostMapping
	public String crearPrueba(@RequestBody String json) {
		return "prueba c reada con exito";
	}
	
	@DeleteMapping("/{id}")
	public void eliminarPrueba(@PathVariable Long id) {
		
	}
	
	@PutMapping("/{id}")
	public String actualziarPrueba() {
		return "actualizada";
	}
}











