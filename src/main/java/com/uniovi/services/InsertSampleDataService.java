package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.entities.types.Role;

@Service
public class InsertSampleDataService {

	@Autowired
	private UsersService usersService;
	@Autowired
	private SaleService saleService;

	@PostConstruct
	public void init() {
		User admin = new User("admin@email.com", "Administrador", "",
				Role.ROLE_ADMIN);
		admin.setPassword("admin");
		User user1 = new User("user1@email.com", "Usuario 1", "",
				Role.ROLE_STAND);
		user1.setPassword("password");
		User user2 = new User("user2@email.com", "Usuario 2", "",
				Role.ROLE_STAND);
		user2.setPassword("password");
		User user3 = new User("user3@email.com", "Usuario 3", "",
				Role.ROLE_STAND);
		user3.setPassword("password");
		User user4 = new User("user4@email.com", "Usuario 4", "",
				Role.ROLE_STAND);
		user4.setPassword("password");
		User user5 = new User("user5@email.com", "Usuario 5", "",
				Role.ROLE_STAND);
		user5.setPassword("password");
		User user6 = new User("user6@email.com", "Usuario 6", "",
				Role.ROLE_STAND);
		user6.setPassword("password");

		usersService.addUser(admin);
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);

		Sale s1 = new Sale("Vendo Ford Fiesta", "En perfecto estado", 4560.2);
		saleService.addSale(s1, user1);
		Sale s2 = new Sale("Vendo iPhone 4", "Con arañazos", 120);
		saleService.addSale(s2, user1);
		Sale s3 = new Sale("Vendo Play 3", "En perfecto estado", 80);
		saleService.addSale(s3, user1);

	}

}
