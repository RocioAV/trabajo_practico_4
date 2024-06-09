package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/materia")
public class MateriaController {

	
	@GetMapping("/form")
	public String getNuevaMateriaPage(Model model) {
		model.addAttribute("titulo", "Nueva Materia");
		return "formMateria";
	}
	
}
