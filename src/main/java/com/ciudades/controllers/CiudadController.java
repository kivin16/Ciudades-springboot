package com.ciudades.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ciudades.models.CiudadModel;
import com.ciudades.services.CiudadService;

@RestController
@RequestMapping("/suggestions")
public class CiudadController {
	@Autowired
	CiudadService ciudadService;
	
//	@GetMapping()
//	public ArrayList<CiudadModel> obtenerCiudades(){
//		return ciudadService.obtenerCiudades();
//	}
	
	@PostMapping()
	public CiudadModel guardarCiudad(@RequestBody CiudadModel ciudad) {
		return this.ciudadService.guardarCiudad(ciudad);
	}
	
	@GetMapping( path = "/{geonameid}")
	public Optional<CiudadModel> obtenerCiudadPorId(@PathVariable("geonameid") int geonameid){
		return this.ciudadService.obtenerPorId(geonameid);
	}
	
//	@GetMapping( path = "/q")
//	public ArrayList<CiudadModel> obtenerCiudadPorNombre(@RequestParam("name") String name){
//		return this.ciudadService.obtenerPorNombre(name);
//	}
	
	@GetMapping()
	public ArrayList<CiudadModel> obtenerCiudadPorNombre(@RequestParam Map<String,String> Parametros){
		if(Parametros.size() >0 && Parametros.containsKey("q")) {		
			return this.ciudadService.obtenerCiudades(Parametros);
		}else{
			return ciudadService.obtenerCiudades();
		}
	}
	
	
}
