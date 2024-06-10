package ar.edu.unju.fi.model;


import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
public class Docente {
	private String legajo;
	private String nombre;
	private String apellido;
	private String email;
	private Long telefono;
	
	
	
}
