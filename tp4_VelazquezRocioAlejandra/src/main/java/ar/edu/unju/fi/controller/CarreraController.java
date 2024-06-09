package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.collection.CollectionAlumno;
import ar.edu.unju.fi.collection.CollectionCarrera;

@Controller
@RequestMapping("/carrera")
public class CarreraController {

	@GetMapping("/list")
	public String getCarrerasPage(Model model) {
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo", "Listado Carreras");
		return "listCarrera";
	}
	
	@GetMapping("/form")
	public String getNuevaCarreraPage(Model model) {
		model.addAttribute("titulo", "Nueva Carrera");
		return "formCarrera";
	}
	
}
