package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.fi.collection.CollectionCarrera;
import ar.edu.unju.fi.collection.CollectionDocente;
import ar.edu.unju.fi.collection.CollectionMateria;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

@Controller
@RequestMapping("/materia")
public class MateriaController {
	@Autowired
	 private Materia materia;

	@Autowired
	private Docente docente;

	@Autowired
	private Carrera carrera;
	
	
	 /**
     * Maneja la solicitud GET para la página de listado de materias.
     *
     * @param model el modelo para la vista.
     * @return el nombre de la vista del listado de materias.
     */
	@GetMapping("/list")
	public String getMateriasPage(Model model) {
		model.addAttribute("materias", CollectionMateria.getMaterias());
		model.addAttribute("titulo", "Listado Materias");
		return "listMateria";
	}
	
	
	/**
     * Maneja la solicitud GET para la página de creación de una nueva materia.
     *
     * @param model el modelo para la vista.
     * @return el nombre de la vista del formulario de materia.
     */
	@GetMapping("/nuevo")
	public String getNuevaMateriaPage(Model model) {
		boolean edicion = false;
		model.addAttribute("titulo", "Nueva Materia");
		model.addAttribute("materia", materia);
		model.addAttribute("edicion", edicion);
	    model.addAttribute("carreras", CollectionCarrera.getCarreras());
	    model.addAttribute("docentes", CollectionDocente.getDocentes());
		return "formMateria";
	}
	
	
	/**
     * Maneja la solicitud POST para guardar una nueva materia.
     *
     * @param materia el objeto materia a guardar.
     * @return el ModelAndView con el listado de materias actualizado.
     */
	@PostMapping("/crear")
	public ModelAndView guardarMateria(@ModelAttribute("materia") Materia materia) {
        ModelAndView modelView = new ModelAndView("listMateria");
        docente = CollectionDocente.buscarDocente(materia.getDocente().getLegajo());
        carrera = CollectionCarrera.buscarCarrera(materia.getCarrera().getCodigo());
        materia.setDocente(docente);
        materia.setCarrera(carrera);
        if (CollectionMateria.agregarMateria(materia)) {
            modelView.addObject("materias", CollectionMateria.getMaterias());
            modelView.addObject("titulo", "Nueva Materia");
        }
        return modelView;
    }
	
	
	 /**
     * Maneja la solicitud GET para la página de edición de una materia.
     *
     * @param model el modelo para la vista.
     * @param codigo el código de la materia a editar.
     * @return el nombre de la vista del formulario de materia.
     */
	@GetMapping("/editar/{codigo}")
	public String getEditarMateriaPage(Model model, @PathVariable(value = "codigo") String codigo) {
        boolean edicion = true;
        Materia materiaEncontrada = new Materia();
        materiaEncontrada = CollectionMateria.buscarMateria(codigo);
        model.addAttribute("titulo", "Modificar Materias");
        model.addAttribute("edicion", edicion);
        model.addAttribute("materia", materiaEncontrada);
        model.addAttribute("carreras", CollectionCarrera.getCarreras());
        model.addAttribute("docentes", CollectionDocente.getDocentes());
        return "formMateria";
    }
	
	
	/**
     * Maneja la solicitud POST para modificar una materia existente.
     *
     * @param materia el objeto materia con la información actualizada.
     * @param redirectAttributes atributos para redirección.
     * @return una redirección al listado de materias.
     */
	@PostMapping("/modificar")
	 public String modificarMateria(@ModelAttribute("materia") Materia materia, RedirectAttributes redirectAttributes) {
        docente = CollectionDocente.buscarDocente(materia.getDocente().getLegajo());
        carrera = CollectionCarrera.buscarCarrera(materia.getCarrera().getCodigo());
        materia.setDocente(docente);
        materia.setCarrera(carrera);
        CollectionMateria.modificarMateria(materia);
        redirectAttributes.addFlashAttribute("isUpdated", true);
        System.out.println(materia);
        return "redirect:/materia/list";
    }
	
	
	/**
     * Maneja la solicitud GET para eliminar una materia.
     *
     * @param codigo el código de la materia a eliminar.
     * @return una redirección al listado de materias.
     */
	 @GetMapping("/eliminar/{codigo}")
	 public String eliminarMateria(@PathVariable(value = "codigo") String codigo) {
	        CollectionMateria.eliminarMateria(codigo);
	        return "redirect:/materia/list";
	    }
	
}
