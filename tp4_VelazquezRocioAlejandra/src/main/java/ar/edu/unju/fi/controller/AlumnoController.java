package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	@GetMapping("/form")
	public String getNuevoAlumnoPage(Model model) {
		model.addAttribute("titulo", "Nuevo Alumno");
		return "formAlumno";
	}
}
