package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.collection.CollectionDocente;
import ar.edu.unju.fi.collection.CollectionMateria;

@Controller
@RequestMapping("/materia")
public class MateriaController {

	
	@GetMapping("/list")
	public String getMateriasPage(Model model) {
		model.addAttribute("docentes", CollectionMateria.getMaterias());
		model.addAttribute("titulo", "Listado Materias");
		return "listMateria";
	}
	
	@GetMapping("/form")
	public String getNuevaMateriaPage(Model model) {
		model.addAttribute("titulo", "Nueva Materia");
		return "formMateria";
	}
	
}
