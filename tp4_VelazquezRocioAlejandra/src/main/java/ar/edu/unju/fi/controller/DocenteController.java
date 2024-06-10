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

import ar.edu.unju.fi.collection.CollectionCarrera;
import ar.edu.unju.fi.collection.CollectionDocente;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	@Autowired
	private Docente docente;

	@GetMapping("/list")
	public String getDocentesPage(Model model) {
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("titulo", "Listado Docentes");
		return "listDocente";
	}
	
	@GetMapping("/nuevo")
	public String getNuevoDocentePage(Model model) {
		boolean edicion=false;
		model.addAttribute("docente", docente);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nuevo Docente");
		return "formDocente";
	}
	
	@PostMapping("/crear")
	public ModelAndView guardarDocente(@ModelAttribute("docente") Docente docente) {
		ModelAndView modelView = new ModelAndView("listDocente");
		CollectionDocente.agregarDocente(docente);
		modelView.addObject("docentes", CollectionDocente.getDocentes()); 
		return modelView;
	}
	
	@GetMapping("/editar/{legajo}")
	public String getModificarDocentePage(Model model, @PathVariable(value="legajo") String legajo) {
		boolean edicion = true;
		Docente docenteEncontrado= new Docente();
		docenteEncontrado= CollectionDocente.buscarDocente(legajo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Modificar Docente");
		model.addAttribute("docente", docenteEncontrado);
		return "formDocente";
	}
	
	@PostMapping("/modificar")
	 public String editarDocente(@ModelAttribute("docente") Docente docente) {
		
        CollectionDocente.modificarDocente(docente);
        return "redirect:/docente/list";
    }
	
	 @GetMapping("/eliminar/{legajo}")
	    public String eliminarCarrera(@PathVariable(value = "legajo") String legajo) {
	        CollectionDocente.eliminarDocente(legajo);
	        return "redirect:/docente/list";
	    }
	
}
