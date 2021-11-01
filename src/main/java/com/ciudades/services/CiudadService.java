package com.ciudades.services;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciudades.models.CiudadModel;
import com.ciudades.repositories.CiudadRepository;

@Service
public class CiudadService {
	@Autowired
	CiudadRepository ciudadrepository;
	
	//Insertar un nuevo registro a la BD
	public CiudadModel guardarCiudad(CiudadModel ciudad) {
		return ciudadrepository.save(ciudad);
	}
	//optional por si no regresa datos y no marque error
	public Optional<CiudadModel> obtenerPorId(int geonameid){
		return ciudadrepository.findById(geonameid);
	}
	
	public ArrayList<CiudadModel> obtenerPorNombre(String name){
		return ciudadrepository.findByName(name);
	}
	
	
	
	//Obtener todos los registros de ciudades
	public ArrayList<CiudadModel> obtenerCiudades(){
		return (ArrayList<CiudadModel>) ciudadrepository.findAll();
	}
	//Verificar parametros
	public ArrayList<CiudadModel> obtenerCiudades(Map<String,String> Parametros){
		//REGLAS 	
		//si el parametro q tiene la misma cantidad de caracteres y empieza igual,puntos = 1,
			//si la palabra tiene el doble o mas = 0.7 - (0.02 * longitud extra despues del doble )
			//si tiene menos del doble  = 0.8 - (0.01 * longitud extra despues del doble ) 
		
		// si el parametro q tiene la misma cantidad de caracteres y no empieza igual = 0.5
			//si la palabra tiene el doble o mas = 0.4 - (0.02 * longitud extra despues del doble )
			//si tiene menos del doble  = 0.5 - (0.01 * longitud extra despues del doble ) 
		
		//para lat y long se verifica positivo o negativo 
			// si tienen diferente signo = 0.5 - la diferencia entre valores absolutos(si el resultado tiene 1 digito = 0.01,si tiene 2 digitos = 0.1)
			//si tienen mismo signo = 0.8 -
		
		Double latitude=0.0;
		Double longitude=0.0;
		int restaNombre=0;
		Double restaLatitude=0.0;
		Double restaLongitude=0.0;
		
		if(Parametros.containsKey("latitude")) {
			try {
				latitude = Double.parseDouble(Parametros.get("latitude"));
		    } catch (NumberFormatException nfe) {
		        
		    }
		}
		if(Parametros.containsKey("longitude")) {
			try {
				longitude = Double.parseDouble(Parametros.get("longitude"));
		    } catch (NumberFormatException nfe) {
		        
		    }
		}
		
		ArrayList<CiudadModel> Ciudades = ciudadrepository.obtenerCiudades((String)Parametros.get("q"));
		
		return Ciudades;
	}

	
	
	

}
