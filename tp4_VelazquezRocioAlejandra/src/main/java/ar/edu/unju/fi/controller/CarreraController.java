package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carrera")
public class CarreraController {

	
	@GetMapping("/form")
	public String getNuevaCarreraPage(Model model) {
		model.addAttribute("titulo", "Nueva Carrera");
		return "formCarrera";
	}
	
}
