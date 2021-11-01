package com.ciudades.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		//si el parametro q tiene la misma cantidad de caracteres ,puntos = 1,
			//si la palabra tiene el doble o mas = 0.8 - (0.02 * longitud extra despues del doble )
			//si tiene menos del doble  = 0.9 - (0.01 * longitud extra despues del nombre ) 
		
		//para lat y long se verifica positivo o negativo 
			// si tienen diferente signo = 0.5 - la diferencia entre valores absolutos(si el resultado tiene 1 digito = 0.01,si tiene 2 digitos = 0.1)
			//si tienen mismo signo = 0.8 - /// esto queda obsoleto
		//AHORA solo resto 1 - la resta entre los valores absolutos y 0.9 - la resta entre los valores absolutos
		
		Double latitude=0.0;
		Double longitude=0.0;
		int nombreLongitud = Parametros.get("q").length();
		Double tlatitud = 0.0;
		Double tlongitud = 0.0;
		Double tnombre;
		Double total;
		
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
		for(CiudadModel ciudadModel : Ciudades ) {
			if(nombreLongitud == ciudadModel.getName().length()) {
				if(Parametros.get("q").toLowerCase().equals(ciudadModel.getName().toLowerCase())) {
					tnombre = 1.0;
				}else {
					tnombre = 0.5;
				}
				
			}else  {
				if(ciudadModel.getName().length()>(nombreLongitud)) {
					tnombre = 0.85 - (0.05 * Math.abs(ciudadModel.getName().length()-nombreLongitud));
				}else {
					tnombre = 0.9 - (0.01 * Math.abs(ciudadModel.getName().length()-nombreLongitud));
				}
				
			}
			//lat y long . Aqui podria usar un metodo que reciba parametros para no duplicar 
			if(latitude == 0.0) {
				if(longitude == 0.0) {
					total=tnombre;
				}else {
					tlongitud = total(longitude,ciudadModel.getLongitude());	
					total = (tnombre+tlongitud)/2;
				}				
			}else {
				if(longitude == 0.0) {
					tlatitud = total(latitude,ciudadModel.getLatitude());
					total=(tnombre+tlatitud)/2;
				}else {
					tlatitud = total(latitude,ciudadModel.getLatitude());
					tlongitud = total(longitude,ciudadModel.getLongitude());			
					total = (tnombre+tlatitud+tlongitud)/3;
				}
			}
			
			ciudadModel.setScore(total);			
		}
		Collections.sort(Ciudades, Comparator.comparing(CiudadModel::getScore).reversed());		
		return Ciudades;
	}
	
	public Double total(Double l,Double modelo) {
		Double t=0.0;
		int cont=0;
		Double resta = 0.05*(Math.abs(l - modelo));
		
		while(resta>=1) {
			resta = resta * 0.1;
			
		}
		if(l >= 0) {
			if(modelo >= 0 ) {
				t = 0.9 - resta ;
			}
			else {
				t = 0.7 - resta ;
			}
		}else {
			if(modelo < 0) {
				t = 0.9 - resta;
			}
			else{
				t = 0.7 - resta;
			}
			
		}
		
		return t;
	}

	
	
	

}
