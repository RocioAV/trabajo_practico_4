package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/docente")
public class DocenteController {

	
	@GetMapping("/form")
	public String getNuevoDocentePage(Model model) {
		model.addAttribute("titulo", "Nuevo Docente");
		return "formDocente";
	}
	
}
