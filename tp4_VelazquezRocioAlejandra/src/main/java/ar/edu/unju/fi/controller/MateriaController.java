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
	
	@GetMapping("/list")
	public String getMateriasPage(Model model) {
		model.addAttribute("materias", CollectionMateria.getMaterias());
		model.addAttribute("titulo", "Listado Materias");
		return "listMateria";
	}
	
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
	
	 @GetMapping("/eliminar/{codigo}")
	 public String eliminarMateria(@PathVariable(value = "codigo") String codigo) {
	        CollectionMateria.eliminarMateria(codigo);
	        return "redirect:/materia/list";
	    }
	
}
