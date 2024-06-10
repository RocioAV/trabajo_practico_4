package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
public class Carrera {
	private String codigo;
	private String nombre;
	private int cantAnios;
	private boolean estado;
	
	
	
	
}
