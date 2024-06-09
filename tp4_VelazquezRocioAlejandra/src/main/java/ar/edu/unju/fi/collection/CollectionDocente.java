package ar.edu.unju.fi.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Docente;

@Component
public class CollectionDocente {
	
	
	private static List<Docente> docentes = new ArrayList<Docente>();
	
	/**
	 * devuelve un arraylist de objetos de la clase carrera
	 */
	public static List<Docente> getDocentes(){
		if(docentes.isEmpty()) {
			docentes.add(new Docente("0001", "Hector Pedo", "Liberatori", "liberatori@gmail.com", 3885789456L));
			docentes.add(new Docente("0002", "Maria Elena", "Gonzalez", "gonzalez@gmail.com", 3885671234L));
            docentes.add(new Docente("0003", "Juan Carlos", "Perez", "perez@gmail.com", 3885567890L));
            docentes.add(new Docente("0004", "Ana Lucia", "Rodriguez", "rodriguez@gmail.com", 3885456789L));
		}
		return docentes;
	}
	
	/**
     * Agrega un nuevo objeto Docente al arraylist
     * @param docente
     */
    public static void agregarDocente(Docente docente) {
        docentes.add(docente);
    }
	
	
    /**
     * Elimina un objeto del arraylist según el código
     * @param codigo
     */
    public static void eliminarDocente(String legajo) {
        Iterator<Docente> iterator = docentes.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getLegajo().compareTo(legajo) == 0) {
                iterator.remove();
            }
        }
    }
	
    /**
     * Modifica un objeto Docente con los nuevos valores pasados por parámetro
     * @param docente
     */
    public static void modificarDocente(Docente docente) {
        for (Docente doc : docentes) {
            if (doc.getLegajo().compareTo(docente.getLegajo()) == 0) {
                doc.setNombre(docente.getNombre());
                doc.setApellido(docente.getApellido());
                doc.setEmail(docente.getEmail());
                doc.setTelefono(docente.getTelefono());
            } else {
                System.out.println("No se encuentra el código de docente");
            }
        }
    }
	
    /**
     * Busca un objeto Docente en el arraylist, según el código
     * @param codigo
     * @return
     */
    public static Docente buscarDocente(String legajo) {
        Predicate<Docente> filterCodigo = d -> d.getLegajo().compareTo(legajo) == 0;
        Optional<Docente> docente = docentes.stream().filter(filterCodigo).findFirst();
        if (docente.isPresent()) {
            return docente.get();
        } else {
            return null;
        }
    }
}
