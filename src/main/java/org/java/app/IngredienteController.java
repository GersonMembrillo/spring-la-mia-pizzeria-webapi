package org.java.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredienteController {
	
	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private IngredienteServ ingredienteServ;
	
	@GetMapping
	public String getIndex(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteServ.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "ingrediente-index";
	}
	
	@GetMapping("/create")
	public String getCreate(Model model) {
		
		model.addAttribute("ingrediente", new Ingrediente());
		
		return "ingrediente-create";
	}
	
	@PostMapping("/create")
	public String storeIngrediente(
			@Valid @ModelAttribute Ingrediente ingrediente,
			BindingResult bindingResult,
			Model model
		) {
		
		if (bindingResult.hasErrors()) {
			
			return "ingrediente-create";
			
		}
			
			ingredienteServ.save(ingrediente);
				
		return "redirect:/ingredients";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteIngrediente(
			@PathVariable int id
		) {
		
		Ingrediente ingrediente = ingredienteServ.findById(id);

		for (Pizza p : ingrediente.getPizze()) {
			
			p.deleteIngrediente(ingrediente);
			pizzaServ.save(p);
		}

		ingredienteServ.delete(ingrediente);
		
		return "redirect:/ingredients";
	}
}
