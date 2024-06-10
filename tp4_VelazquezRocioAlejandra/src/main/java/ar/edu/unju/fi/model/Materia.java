package ar.edu.unju.fi.model;



import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
public class Materia {
	private String codigo;
	private String nombre;
	private String curso;
	private int cantHoras;
	private String modalidad;
	private Docente docente;
	private Carrera carrera;
	
	
	
}
