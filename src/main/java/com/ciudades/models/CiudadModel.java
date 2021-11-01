package com.ciudades.models;

//import javax.persistence.Entity;
//import javax.persistence.Table;
import javax.persistence.*;
//import java.math.BigDecimal;

@Entity
@Table(name="ciudades")

public class CiudadModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int geonameid;  //integer
	
	private String name;	//utf8 varchasr(200)
	private String code;	//varchar(10)
	private String country;	//ISO-3166 2-letter country code, 2 characters
	private String continent; //continent abbreviation
	private int elevation;		//elevation
	private double	latitude; // latitude in decimal degrees (wgs84)
	private double longitude; // longitude in decimal degrees (wgs84)
	private int population;	// bigint (8 byte int)
	private double score;	//score that the user assigns
	
	public int getGeonameid() {
		return geonameid;
	}
	public void setGeonameid(int geonameid) {
		this.geonameid = geonameid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public int getElevation() {
		return elevation;
	}
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}	
	
	
	
	
}
