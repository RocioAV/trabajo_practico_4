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

	
	
	/**
     * Maneja la solicitud GET para la página de listado de carreras.
     *
     * @param model el modelo para la vista.
     * @return el nombre de la vista del listado de las carreras.
     */
	@GetMapping("/list")
	public String getCarrerasPage(Model model) {
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo", "Listado Carreras");
		return "listCarrera";
	}
	
	
	/**
     * Maneja la solicitud GET para la página de creación de una nueva carrera.
     *
     * @param model el modelo para la vista.
     * @return el nombre de la vista del formulario de carrera.
     */
	@GetMapping("/nuevo")
	public String getNuevaCarreraPage(Model model) {
		boolean edicion=false;
		model.addAttribute("carrera", carrera);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva Carrera");
		return "formCarrera";
	}
	
	
	
	/**
     * Maneja la solicitud POST para guardar una nueva carrera.
     *
     * @param carrera el objeto carrera a guardar.
     * @return el ModelAndView con el listado de carreras actualizadas.
     */
	@PostMapping("/crear")
	public ModelAndView guardarCarrera(@ModelAttribute("carrera") Carrera carrera) {
		ModelAndView modelView = new ModelAndView("listCarrera");
		CollectionCarrera.agregarCarrera(carrera);
		modelView.addObject("carreras", CollectionCarrera.getCarreras()); 
		return modelView;
	}
	
	
	/**
     * Maneja la solicitud GET para la página de edición de una carrera.
     *
     * @param model el modelo para la vista.
     * @param codigo el codigo unico que identifica la carrera a editar.
     * @return el nombre de la vista del formulario de carrera.
     */
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
	
	
	/**
     * Maneja la solicitud POST para modificar una carrera existente.
     *
     * @param carrera el objeto carrera con la información actualizada.
     * @return una redirección al listado de carreras.
     */
	@PostMapping("/modificar")
	 public String editarCarrera(@ModelAttribute("carrera") Carrera carrera) {
		
        CollectionCarrera.modificarCarrera(carrera);
        return "redirect:/carrera/list";
    }
	
	
	
	/**
     * Maneja la solicitud GET para eliminar una carrera.
     *
     * @param codigo el codigo unico de la carrera a eliminar.
     * @return una redirección al listado de carreras.
     */
	 @GetMapping("/eliminar/{codigo}")
	    public String eliminarCarrera(@PathVariable(value = "codigo") String codigo) {
	        CollectionCarrera.eliminarCarrera(codigo);
	        return "redirect:/carrera/list";
	    }
	
}
