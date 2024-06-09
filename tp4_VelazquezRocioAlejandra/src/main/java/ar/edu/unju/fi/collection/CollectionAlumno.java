package ar.edu.unju.fi.collection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;

@Component
public class CollectionAlumno {
	
	private static List<Alumno> alumnos = new ArrayList<Alumno>();
	
	/**
	 * devuelve un arraylist de objetos de la clase carrera
	 */
	public static List<Alumno> getAlumnos() {
        if (alumnos.isEmpty()) {
            alumnos.add(new Alumno("44351449", "Rocio Alejandra", "Velazquez", "ro@gmail.com", 3885123456L, LocalDate.of(2002, 12, 2), "Av siempre viva 122", "APU5596"));
            alumnos.add(new Alumno("45452485", "Noemi Ayelen", "Saravia", "noe@gmail.com", 3885456789L, LocalDate.of(2003, 06, 30), "Av siempre viva 1532", "APU5495"));
            alumnos.add(new Alumno("44987562", "Mauricio Luca", "Suilice", "mauri@gmail.com", 3885159753L, LocalDate.of(2002, 05, 04), "Peru 122", "APU4596"));
            alumnos.add(new Alumno("40487521", "Nehuen", "Velazquez", "nehuen@gmail.com", 3884785214L, LocalDate.of(1996, 10, 29), "Manantiales 132", "APU4495"));
            alumnos.add(new Alumno("44351449", "Lucas Enrique", "Cari", "cari@gmail.com", 38854896523L, LocalDate.of(2002, 03, 11), "Las Rosas 12", "APU5698"));
        }
        return alumnos;
    }
	
	/**
	 * Agrega un nuevo objeto Alumno al arraylist
	 * @param alumno
	 */
	public static void agregarAlumno(Alumno alumno) {
		alumnos.add(alumno);
	}
	
	
	/**
	 * Elimina un objeto del arraylist segun el codigo
	 * @param codigo
	 */
	public static void eliminarAlumno(String lu) {
		Iterator<Alumno> iterator = alumnos.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getLu().compareTo(lu)== 0) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * modifica un objeto Alumno con lo nuevos valores pasador por param 
	 * @param carrera
	 */
	public static void modificarAlumno (Alumno alumno) {
		for (Alumno al : alumnos) {
			if(al.getLu().compareTo(al.getLu())==0) {
				al.setNombre(alumno.getNombre());
				al.setApellido(alumno.getApellido());
				al.setDni(alumno.getDni());
				al.setDomicilio(alumno.getDomicilio());
				al.setEmail(alumno.getEmail());
				al.setTelefono(alumno.getTelefono());
				al.setFechaNac(alumno.getFechaNac());
			}else {
				System.out.println("no se enccuentra el lu del alumno");
			}
		}
	}
	
	/**
	 * busca un objeto alumno en el arraylist, segun el LU
	 * @param codigo
	 * @return
	 */
	public static Alumno buscarAlumno(String lu) {
		Predicate<Alumno> filterCodigo = c -> c.getLu().compareTo(lu)==0;
		Optional<Alumno> alumno = alumnos.stream().filter(filterCodigo).findFirst();
		if (alumno.isPresent()) {
			return alumno.get();
		}else {
			return null;
		}
	}
}
