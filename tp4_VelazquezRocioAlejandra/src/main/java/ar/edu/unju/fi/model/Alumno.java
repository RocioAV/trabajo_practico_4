package ar.edu.unju.fi.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
public class Alumno {
	private String dni;
	private String  nombre;
	private String apellido;
	private String email;
	private Long telefono;
	private LocalDate fechaNac;
	private String domicilio;
	private String lu;
	
	
	
	
	
}
