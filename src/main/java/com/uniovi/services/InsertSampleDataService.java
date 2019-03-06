package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.entities.types.Rol;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UserService usersService;

	 
	 
	@PostConstruct
	public void init() {
		User admin = new User("admin@email.com", "Administrador", "", Rol.ADMIN);
		admin.setPassword("admin");
		User user1 = new User("user1@email.com", "Usuario 1", "", Rol.STAND);
		user1.setPassword("password");
		User user2 = new User("user2@email.com", "Usuario 2", "", Rol.STAND);
		user2.setPassword("password");
		User user3 = new User("user3@email.com", "Usuario 3", "", Rol.STAND);
		user3.setPassword("password");
		User user4 = new User("user4@email.com", "Usuario 4", "", Rol.STAND);
		user4.setPassword("password");
		User user5 = new User("user5@email.com", "Usuario 5", "", Rol.STAND);
		user5.setPassword("password");
		User user6 = new User("user6@email.com", "Usuario 6", "", Rol.STAND);
		user6.setPassword("password");
		
		
		usersService.addUser(admin);
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}

}
