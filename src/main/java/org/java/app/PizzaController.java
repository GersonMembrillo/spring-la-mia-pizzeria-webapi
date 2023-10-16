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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PizzaController {

	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private OffertaServ offertaServ;
	
	@Autowired 
	private IngredienteServ ingredienteServ;

	@GetMapping
	public String getIndex(@RequestParam(required = false, name = "search") String searchTitle, Model model) {

		System.out.println("search: " + searchTitle);

//		List<Pizza> pizze = pizzaServ.findAll();
		List<Pizza> pizze = searchTitle == null ? pizzaServ.findAll() : pizzaServ.findByName(searchTitle);

		model.addAttribute("pizze", pizze);
		model.addAttribute("searchTitle", searchTitle);

		return "pizza-index";
	}

	@GetMapping("/{id}")
	public String getShow(@PathVariable Integer id, Model model) {
		
		Pizza pizza = pizzaServ.findById(id);
		model.addAttribute("pizza", pizza);
		
		return "pizza-show";
	}

	@GetMapping("/create")
	public String getCreateForm(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteServ.findAll();

		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredienti", ingredienti);

		return "pizza-create";
	}

	@PostMapping("/create")
	public String storeNewPizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {

		return storePizza(pizza, bindingResult);
	}

	@GetMapping("/edit/{id}")
	public String getEditForm(@PathVariable int id, Model model) {
		
		List<Ingrediente> ingredienti = ingredienteServ.findAll();
		Pizza pizza = pizzaServ.findById(id);
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredienti", ingredienti);

		return "pizza-create";
	}

	@PostMapping("/edit/{id}")
	public String updatePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {

		return storePizza(pizza, bindingResult);
	}

	@PostMapping("/delete/{id}")
	public String deletePizza(@PathVariable int id) {

		Pizza pizza = pizzaServ.findById(id);
		pizzaServ.deletePizza(pizza);

		return "redirect:/";
	}
	
	@GetMapping("/offerta/create/{pizza_id}")
	public String getOffertaForm(@PathVariable("pizza_id") int id,
			Model model
		) {
		
		Pizza pizza = pizzaServ.findById(id);
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("offerta", new Offerta());
		
		return "offerta-create";
	}

	@PostMapping("/offerta/create/{pizza_id}")
	public String storeOfferta(
			@Valid @ModelAttribute Offerta Offerta,
			BindingResult bindingResult,
			@PathVariable("pizza_id") int id,
			Model model
		) {
		
		if (bindingResult.hasErrors()) {
			
			return "offerta-create"; 
		}
		
		Pizza pizza = pizzaServ.findById(id);
		Offerta.setPizza(pizza);
		
		offertaServ.save(Offerta);
		
		return "redirect:/";

	}

	@GetMapping("/offerta/edit/{offerta_id}")
	public String editOfferta(
			@PathVariable("offerta_id") int id,
			Model model
		) {
		
		Offerta offerta = offertaServ.findById(id);
		Pizza pizza = offerta.getPizza();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("offerta", offerta);
		
		return "offerta-create"; 
	}

	@PostMapping("/offerta/edit/{offerta_id}")
	public String updateOfferta(@Valid @ModelAttribute Offerta offerta,
			BindingResult bindingResult,
			@PathVariable("offerta_id") int id,
			Model model
		) {
		
		if (bindingResult.hasErrors()) {
			
			return "offerta-create"; 
		}
		
		Offerta oldOfferta = offertaServ.findById(id);
		Pizza pizza = oldOfferta.getPizza();
		offerta.setPizza(pizza);
		
		offertaServ.save(offerta);
		
		return "redirect:/pizzas/" + pizza.getId();
	}
	
	private String storePizza(Pizza pizza, BindingResult bindingResult) {
		
		System.out.println("pizza:\n" + pizza);

		if (bindingResult.hasErrors()) {

			System.out.println("Errors");
			bindingResult.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
				.forEach(System.out::println);

			return "pizza-create";
		}

		pizzaServ.save(pizza);

		return "redirect:/";
	}
}