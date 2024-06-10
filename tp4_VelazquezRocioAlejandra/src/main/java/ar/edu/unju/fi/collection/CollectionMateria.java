package ar.edu.unju.fi.collection;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

@Component
public class CollectionMateria {
	
	private static List<Materia> materias = new ArrayList<Materia>();
	
	/**
	 * devuelve un arraylist de objetos de la clase Materia
	 */
	public static List<Materia> getMaterias() {
		if(materias.isEmpty()) {
        materias.add(new Materia("1111", "Programacion Estructurada", "1ro", 6, "virtual", CollectionDocente.getDocentes().get(0), CollectionCarrera.getCarreras().get(0)));
        materias.add(new Materia("2222", "Organizacion de computadoras", "2do", 6, "Presencial", CollectionDocente.getDocentes().get(1), CollectionCarrera.getCarreras().get(2)));
        materias.add(new Materia("3333", "Matematica Discreta", "3ro", 5, "presencial", CollectionDocente.getDocentes().get(2), CollectionCarrera.getCarreras().get(3)));
		}
        return materias;
    }
	
	/**
     * Agrega un nuevo objeto Materia al ArrayList
     * @param materia
     */
    public static boolean agregarMateria(Materia materia) {
        materias.add(materia);
        return true;
    }
	
	
    /**
     * Elimina un objeto del ArrayList según el código
     * @param codigo
     */
    public static void eliminarMateria(String codigo) {
        Iterator<Materia> iterator = materias.iterator();
        while(iterator.hasNext()) {
            if(iterator.next().getCodigo().equals(codigo)) {
                iterator.remove();
            }
        }
    }
	
    /**
     * Modifica un objeto Materia con los nuevos valores pasados por parámetro
     * @param materia
     */
    public static void modificarMateria(Materia materia) {
        for (Materia mat : materias) {
            if(mat.getCodigo().equals(materia.getCodigo())) {
                mat.setNombre(materia.getNombre());
                mat.setCurso(materia.getCurso());
                mat.setCantHoras(materia.getCantHoras());
                mat.setModalidad(materia.getModalidad());
                mat.setDocente(materia.getDocente());
                mat.setCarrera(materia.getCarrera());
            } else {
                System.out.println("No se encuentra el código de la materia");
            }
        }
    }
	
    /**
     * Busca un objeto Materia en el ArrayList, según el código
     * @param codigo
     * @return
     */
    public static Materia buscarMateria(String codigo) {
        Predicate<Materia> filterCodigo = m -> m.getCodigo().equals(codigo);
        Optional<Materia> materia = materias.stream().filter(filterCodigo).findFirst();
        if (materia.isPresent()) {
            return materia.get();
        } else {
            return null;
        }
    }
}
