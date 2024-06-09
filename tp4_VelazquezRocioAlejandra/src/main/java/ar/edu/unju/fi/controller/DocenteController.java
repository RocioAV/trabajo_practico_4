package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.collection.CollectionCarrera;
import ar.edu.unju.fi.collection.CollectionDocente;

@Controller
@RequestMapping("/docente")
public class DocenteController {

	@GetMapping("/list")
	public String getDocentesPage(Model model) {
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("titulo", "Listado Docentes");
		return "listDocente";
	}
	
	@GetMapping("/form")
	public String getNuevoDocentePage(Model model) {
		model.addAttribute("titulo", "Nuevo Docente");
		return "formDocente";
	}
	
}
