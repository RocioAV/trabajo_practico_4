package ar.edu.unju.fi.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;

@Component
public class CollectionCarrera {
	
	private static List<Carrera> carreras = new ArrayList<Carrera>();
	
	/**
	 * devuelve un arraylist de objetos de la clase carrera
	 */
	public static List<Carrera> getCarreras(){
		if(carreras.isEmpty()) {
			carreras.add(new Carrera("1111", "A.P.U", 3, true));
			carreras.add(new Carrera("2222", "Licenciatura en Sistemas", 5, true));
			carreras.add(new Carrera("3333", "Ingenieria Minas", 5, false));
			carreras.add(new Carrera("4444", "Tecnicatura en Perforaciones ", 3, true));
			carreras.add(new Carrera("4444", "Licenciatura Ciencias Geologicas", 5, true));
		}
		return carreras;
	}
	
	/**
	 * Agrega un nuevo objeto Carrera al arraylist
	 * @param carrera
	 */
	public static void agregarCarrera(Carrera carrera) {
		carreras.add(carrera);
	}
	
	
	/**
	 * Elimina un objeto del arraylist segun el codigo
	 * @param codigo
	 */
	public static void eliminarCarrera(String codigo) {
		Iterator<Carrera> iterator = carreras.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getCodigo().compareTo(codigo)== 0) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * modifica un objeto Carrera con lo nuevos valores pasador por param 
	 * @param carrera
	 */
	public static void modificarCarrera (Carrera carrera) {
		for (Carrera carre : carreras) {
			if(carre.getCodigo().compareTo(carrera.getCodigo())==0) {
				carre.setNombre(carrera.getNombre());
				carre.setCantAnios(carrera.getCantAnios());
				carre.setEstado(carrera.isEstado());
			}else {
				System.out.println("no se enccuentra el codigo de carrera");
			}
		}
	}
	
	/**
	 * busca un objeto carrera el arraylist, segun el codigo
	 * @param codigo
	 * @return
	 */
	public static Carrera buscarCarrera(String codigo) {
		Predicate<Carrera> filterCodigo = c -> c.getCodigo().compareTo(codigo)==0;
		Optional<Carrera> carrera = carreras.stream().filter(filterCodigo).findFirst();
		if (carrera.isPresent()) {
			return carrera.get();
		}else {
			return null;
		}
	}
	
	
}
