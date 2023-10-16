package org.java.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteServ {

	@Autowired
	private IngredienteRepo ingredienteRepo;
	
	public List<Ingrediente> findAll() {
		
		return ingredienteRepo.findAll();
	}
	public Ingrediente findById(int id) {
		
		return ingredienteRepo.findById(id).get();
	}
	public void save(Ingrediente ingrediente) {
		
		ingredienteRepo.save(ingrediente);
	}
	
	public void delete(Ingrediente ingrediente) {
		
		ingredienteRepo.delete(ingrediente);
	}
}
