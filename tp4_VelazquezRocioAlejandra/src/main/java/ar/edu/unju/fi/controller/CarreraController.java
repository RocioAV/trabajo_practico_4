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

import ar.edu.unju.fi.collection.CollectionAlumno;
import ar.edu.unju.fi.collection.CollectionCarrera;
import ar.edu.unju.fi.model.Carrera;

@Controller
@RequestMapping("/carrera")
public class CarreraController {
	@Autowired
	private Carrera carrera;

	@GetMapping("/list")
	public String getCarrerasPage(Model model) {
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo", "Listado Carreras");
		return "listCarrera";
	}
	
	@GetMapping("/nuevo")
	public String getNuevaCarreraPage(Model model) {
		boolean edicion=false;
		model.addAttribute("carrera", carrera);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva Carrera");
		return "formCarrera";
	}
	
	@PostMapping("/crear")
	public ModelAndView guardarCarrera(@ModelAttribute("carrera") Carrera carrera) {
		ModelAndView modelView = new ModelAndView("listCarrera");
		CollectionCarrera.agregarCarrera(carrera);
		modelView.addObject("carreras", CollectionCarrera.getCarreras()); 
		return modelView;
	}
	
	@GetMapping("/editar/{codigo}")
	public String getModificarCarreraPage(Model model, @PathVariable(value="codigo") String codigo) {
		boolean edicion = true;
		Carrera carreraEncontrada= new Carrera();
		carreraEncontrada= CollectionCarrera.buscarCarrera(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Modificar Carrera");
		model.addAttribute("carrera", carreraEncontrada);
		return "formCarrera";
	}
	
	@PostMapping("/modificar")
	 public String editarCarrera(@ModelAttribute("carrera") Carrera carrera) {
		
        CollectionCarrera.modificarCarrera(carrera);
        return "redirect:/carrera/list";
    }
	
	 @GetMapping("/eliminar/{codigo}")
	    public String eliminarCarrera(@PathVariable(value = "codigo") String codigo) {
	        CollectionCarrera.eliminarCarrera(codigo);
	        return "redirect:/carrera/list";
	    }
	
}
