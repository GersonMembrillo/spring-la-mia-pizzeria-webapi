package org.java.app;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.java.app.user.Role;
import org.java.app.user.User;
import org.java.app.user.RoleServ;
import org.java.app.user.UserServ;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private OffertaServ offertaServ;
	
	@Autowired
	private IngredienteServ ingredienteServ;
	
	@Autowired
	private RoleServ roleServ;

	@Autowired
	private UserServ userServ;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		Ingrediente ingrediente1 = new Ingrediente("Farina", "di grano tenero");
		Ingrediente ingrediente2 = new Ingrediente("Pomodoro", "San Marzano");
		Ingrediente ingrediente3 = new Ingrediente("Mozzarella", "di bufala");
		Ingrediente ingrediente4 = new Ingrediente("Prosciutto", "cotto");
		Ingrediente ingrediente5 = new Ingrediente("Carciofi", "bio");
		Ingrediente ingrediente6 = new Ingrediente("Basilico", "coltivato");
		Ingrediente ingrediente7 = new Ingrediente("Funghi", "al naturale");
		Ingrediente ingrediente8 = new Ingrediente("Salame", "piccante");
		Ingrediente ingrediente9 = new Ingrediente("Pepe", "dello Sri Lanka");
		Ingrediente ingrediente10 = new Ingrediente("Olio d'oliva", "extravergine");
		Ingrediente ingrediente11 = new Ingrediente("Speak", "a fette");
		Ingrediente ingrediente12 = new Ingrediente("Scamorza", "provola");
		Ingrediente ingrediente13 = new Ingrediente("Patate", "biologiche");
		Ingrediente ingrediente14 = new Ingrediente("Salsiccia", "tedesca");
		Ingrediente ingrediente15 = new Ingrediente("Rosmarino", "di stagione");
		
		ingredienteServ.save(ingrediente1);
		ingredienteServ.save(ingrediente2);
		ingredienteServ.save(ingrediente3);
		ingredienteServ.save(ingrediente4);
		ingredienteServ.save(ingrediente5);
		ingredienteServ.save(ingrediente6);
		ingredienteServ.save(ingrediente7);
		ingredienteServ.save(ingrediente8);
		ingredienteServ.save(ingrediente9);
		ingredienteServ.save(ingrediente10);
		ingredienteServ.save(ingrediente11);
		ingredienteServ.save(ingrediente12);
		ingredienteServ.save(ingrediente13);
		ingredienteServ.save(ingrediente14);
		ingredienteServ.save(ingrediente15);
		
  		Pizza pizza1 = new Pizza("Margherita",
  								 "Un'esplosione di sapori e consistenze, con il pomodoro dolce e acidulo, la mozzarella cremosa e filante e il basilico fresco e aromatico. Un piatto semplice ma perfetto, adatto a tutti i palati.",
  								 "https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg", 10.00, ingrediente1, ingrediente2, ingrediente3, ingrediente6, ingrediente10);
  		Pizza pizza2 = new Pizza("4 Stagioni",
  								 "Un classico intramontabile, con pomodoro, mozzarella, funghi, prosciutto cotto, carciofi e olive. Un mix di sapori e consistenze che conquisterà tutti.",
  								 "https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza_4_stagioni_la_ricetta_fatta_in_casa_.jpg?itok=EgsCQLDd", 12.00, ingrediente1, ingrediente2, ingrediente3, ingrediente4, ingrediente5, ingrediente6, ingrediente7, ingrediente10);
  		Pizza pizza3 = new Pizza("Diavola",
  								 "Un'esplosione di sapore per gli amanti del piccante. La base di pomodoro e mozzarella è arricchita con salame piccante",
  								 "https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza_rustica.jpg?itok=Lbp_jtZW", 14.00, ingrediente1, ingrediente2, ingrediente3, ingrediente8, ingrediente9, ingrediente10);
  		Pizza pizza4 = new Pizza("Speak & Scamorza",
  								 "Una sinfonia di sapori e consistenze, un impasto fragrante e croccante, un sugo di pomodoro fresco e acidulo, una mozzarella cremosa e filante, una scamorza affumicata decisa e saporita.",
  								 "https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza.jpg?itok=z_-RLtOK", 16.00, ingrediente1, ingrediente3, ingrediente10, ingrediente11, ingrediente12);
  		Pizza pizza5 = new Pizza("Patate & Salsiccia",
  								 "Una pizza invernale, con pomodoro, mozzarella, patate e salsiccia. Un piatto sostanzioso e perfetto per la stagione fredda.",
  								 "https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza_con_patate_e_salsiccia.jpg?itok=mxab5rGt", 18.00, ingrediente1, ingrediente3, ingrediente10, ingrediente13, ingrediente14, ingrediente15);
  		
  		pizzaServ.save(pizza1);
  		pizzaServ.save(pizza2);
  		pizzaServ.save(pizza3);
  		pizzaServ.save(pizza4);
  		pizzaServ.save(pizza5);
  		
  		System.out.println("Insert OK");
  		
  		Offerta offerta1 = new Offerta("Due pizze al prezzo di una!", LocalDate.now(), 
				LocalDate.parse("2023-11-20"), pizza1);
		Offerta offerta2 = new Offerta("Paghi 3, prendi 2!", LocalDate.now(), 
				LocalDate.parse("2023-12-25"), pizza2);
		Offerta offerta3 = new Offerta("1 bibita gratis con l'acquisto di 2 pizze!", LocalDate.now(), 
				LocalDate.parse("2023-12-31"), pizza4);
		
		offertaServ.save(offerta1);
		offertaServ.save(offerta2);
		offertaServ.save(offerta3);
		
		
		Role userRole = new Role("USER");
		Role adminRole = new Role("ADMIN");
		
		roleServ.save(userRole);
		roleServ.save(adminRole);
		
		final String pwsUser = new BCryptPasswordEncoder().encode("pws");
		final String pwsAdmin = new BCryptPasswordEncoder().encode("pws");
		
		User ciccioUser = new User("ciccioUser", pwsUser, userRole);
		User ciccioAdmin = new User("ciccioAdmin", pwsAdmin, adminRole);
		
		userServ.save(ciccioUser);
		userServ.save(ciccioAdmin);
		
	}
}