package org.java.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepo extends JpaRepository<Ingrediente, Integer> {

}

