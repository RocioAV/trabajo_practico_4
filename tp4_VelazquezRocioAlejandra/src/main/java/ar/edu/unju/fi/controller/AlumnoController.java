package ar.edu.unju.fi.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collection.CollectionAlumno;
import ar.edu.unju.fi.model.Alumno;


@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	@Autowired
	private Alumno alumno;
	
	
	/**
     * Maneja la solicitud GET para la página de listado de alumnos.
     *
     * @param model el modelo para la vista.
     * @return el nombre de la vista del listado de alumnos.
     */
	@GetMapping("/list")
	public String getAlumnosPage(Model model) {
		model.addAttribute("alumnos", CollectionAlumno.getAlumnos());
		model.addAttribute("titulo", "Listado Alumnos");
		return "listAlumno";
	}
	
	/**
     * Maneja la solicitud GET para la página de creación de un nuevo alumno.
     *
     * @param model el modelo para la vista.
     * @return el nombre de la vista del formulario de alumno.
     */
	@GetMapping("/nuevo")
	public String getNuevoAlumnoPage(Model model) {
		boolean edicion=false;
		model.addAttribute("alumno", alumno);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nuevo Alumno");
		return "formAlumno";
	}
	
	
	/**
     * Maneja la solicitud POST para guardar un nuevo alumno.
     *
     * @param alumno el objeto alumno a guardar.
     * @return el ModelAndView con el listado de alumnos actualizado.
     */
	@PostMapping("/crear")
	public ModelAndView guardarAlumno(@ModelAttribute("alumno") Alumno alumno) {
		ModelAndView modelView = new ModelAndView("listAlumno");
		System.out.println(alumno.toString());
		CollectionAlumno.agregarAlumno(alumno);
		modelView.addObject("alumnos", CollectionAlumno.getAlumnos()); //alumnos debe ser el mismo que pasamos en el html 
//		for (Alumno al : CollectionAlumno.getAlumnos()) {
//			System.out.println(al.toString());
//		}
		return modelView;
	}
	
	
	 /**
     * Maneja la solicitud GET para la página de edición de un alumno.
     *
     * @param model el modelo para la vista.
     * @param lu el número de legajo del alumno a editar.
     * @return el nombre de la vista del formulario de alumno.
     */
	@GetMapping("/editar/{lu}")
	public String getModificarAlumnoPage(Model model, @PathVariable(value="lu") String lu) {
		boolean edicion = true;
		Alumno alumnoEncontrado= new Alumno();
		alumnoEncontrado = CollectionAlumno.buscarAlumno(lu);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Modificar Alumno");
		model.addAttribute("alumno", alumnoEncontrado);
		model.addAttribute("fechaNacPrev", alumnoEncontrado.getFechaNac());
		return "formAlumno";
	}
	
	
	
	/**
     * Maneja la solicitud POST para modificar un alumno existente.
     *
     * @param alumno el objeto alumno con la información actualizada.
     * @return una redirección al listado de alumnos.
     */
	@PostMapping("/modificar")
	 public String editarAlumno(@ModelAttribute("alumno") Alumno alumno) {
		
        CollectionAlumno.modificarAlumno(alumno);
        System.out.println(alumno.toString());
        return "redirect:/alumno/list";
    }
	
	
	/**
     * Maneja la solicitud GET para eliminar un alumno.
     *
     * @param lu el número de legajo del alumno a eliminar.
     * @return una redirección al listado de alumnos.
     */
	 @GetMapping("/eliminar/{lu}")
	    public String eliminarAlumno(@PathVariable(value = "lu") String lu) {
	        CollectionAlumno.eliminarAlumno(lu);
	        return "redirect:/alumno/list";
	    }
	
	
}
