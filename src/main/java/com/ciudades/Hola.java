package com.ciudades;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController	
public class Hola {
	
	@RequestMapping("/")
	public String hola() {
		return "Hola Mundo!!, que tengas un buen día :D";
	}

}
