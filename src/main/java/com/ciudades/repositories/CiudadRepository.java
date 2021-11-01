package com.ciudades.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ciudades.models.CiudadModel;

@Repository
public interface CiudadRepository extends CrudRepository<CiudadModel, Integer>{		
	
	public abstract ArrayList <CiudadModel> findByName(String name);
	
	@Query(value = "SELECT ciu FROM CiudadModel ciu WHERE ciu.name LIKE %?1%")
	public abstract ArrayList <CiudadModel> obtenerCiudades(String name);
	
	

}
