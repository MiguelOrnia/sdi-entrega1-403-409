package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.entities.types.Role;

@Service
public class InsertSampleDataService {

	@Autowired
	private UsersService usersService;
	@Autowired
	private SaleService saleService;
	@Autowired
	private MessagesService messagesService;

	@PostConstruct
	public void init() {
		// Adding users
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
		;
		usersService.addUser(admin);
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);

		// Adding Sales
		Sale s1 = new Sale("Vendo Ford Fiesta", "En perfecto estado", 4560.2);
		Sale s2 = new Sale("Vendo iPhone 4", "Con arañazos", 120);
		Sale s3 = new Sale("Vendo Play 3", "En perfecto estado", 80);
		saleService.addSale(s1, user1);
		saleService.addSale(s2, user1);
		saleService.addSale(s3, user1);

		Sale s12 = new Sale("Vendo Cosa Random 1", "En perfecto estado",
				4560.2);
		Sale s22 = new Sale("Vendo Cosa Random 2", "Con arañazos", 120);
		Sale s32 = new Sale("Vendo Cosa Random 3", "En perfecto estado", 80);
		saleService.addSale(s12, user2);
		saleService.addSale(s22, user2);
		saleService.addSale(s32, user2);

		Sale s13 = new Sale("Vendo Cosa Random 4", "En perfecto estado",
				4560.2);
		Sale s23 = new Sale("Vendo Cosa Random 5", "Con arañazos", 120);
		Sale s33 = new Sale("Vendo Cosa Random 6", "En perfecto estado", 80);
		saleService.addSale(s13, user3);
		saleService.addSale(s23, user3);
		saleService.addSale(s33, user3);

		Sale s14 = new Sale("Vendo Cosa Random 7", "En perfecto estado",
				4560.2);
		Sale s24 = new Sale("Vendo Cosa Random 8", "Con arañazos", 120);
		Sale s34 = new Sale("Vendo Cosa Random 9", "En perfecto estado", 80);
		saleService.addSale(s14, user4);
		saleService.addSale(s24, user4);
		saleService.addSale(s34, user4);

		Sale s15 = new Sale("Vendo Cosa Random 10", "En perfecto estado",
				4560.2);
		Sale s25 = new Sale("Vendo Cosa Random 11", "Con arañazos", 120);
		Sale s35 = new Sale("Vendo Cosa Random 12", "En perfecto estado", 80);
		saleService.addSale(s15, user5);
		saleService.addSale(s25, user5);
		saleService.addSale(s35, user5);

		// Adding purchases

		// Adding conversations
		Conversation chat11 = messagesService.getConversation(user1, s12);
		Conversation chat12 = messagesService.getConversation(user1, s22);
		Conversation chat13 = messagesService.getConversation(user1, s32);

		Conversation chat21 = messagesService.getConversation(user2, s13);
		Conversation chat22 = messagesService.getConversation(user2, s23);
		Conversation chat23 = messagesService.getConversation(user2, s33);

		Conversation chat31 = messagesService.getConversation(user3, s14);
		Conversation chat32 = messagesService.getConversation(user3, s24);
		Conversation chat33 = messagesService.getConversation(user3, s34);

		Conversation chat41 = messagesService.getConversation(user4, s15);
		Conversation chat42 = messagesService.getConversation(user4, s25);
		Conversation chat43 = messagesService.getConversation(user4, s35);

		Conversation chat51 = messagesService.getConversation(user5, s1);
		Conversation chat52 = messagesService.getConversation(user5, s2);
		Conversation chat53 = messagesService.getConversation(user5, s3);

		// Adding messages
		Message m111 = new Message(user1, "Buenos días");
		Message m112 = new Message(user1, "Quería información sobre la oferta");
		Message m113 = new Message(user2, "Buenos días");
		Message m114 = new Message(user2, "Qué quiere saber?");
		messagesService.addMessage(m111, chat11);
		messagesService.addMessage(m112, chat11);
		messagesService.addMessage(m113, chat11);
		messagesService.addMessage(m114, chat11);
		Message m121 = new Message(user1, "Buenos días");
		Message m122 = new Message(user1, "Quería información sobre la oferta");
		Message m123 = new Message(user2, "Buenos días");
		Message m124 = new Message(user2, "Qué quiere saber?");
		messagesService.addMessage(m121, chat12);
		messagesService.addMessage(m122, chat12);
		messagesService.addMessage(m123, chat12);
		messagesService.addMessage(m124, chat12);
		Message m131 = new Message(user1, "Buenos días");
		Message m132 = new Message(user1, "Quería información sobre la oferta");
		Message m133 = new Message(user2, "Buenos días");
		Message m134 = new Message(user2, "Qué quiere saber?");
		messagesService.addMessage(m131, chat13);
		messagesService.addMessage(m132, chat13);
		messagesService.addMessage(m133, chat13);
		messagesService.addMessage(m134, chat13);

		Message m211 = new Message(user2, "Buenos días");
		Message m212 = new Message(user2, "Quería información sobre la oferta");
		Message m213 = new Message(user3, "Buenos días");
		Message m214 = new Message(user3, "Qué quiere saber?");
		messagesService.addMessage(m211, chat21);
		messagesService.addMessage(m212, chat21);
		messagesService.addMessage(m213, chat21);
		messagesService.addMessage(m214, chat21);
		Message m221 = new Message(user2, "Buenos días");
		Message m222 = new Message(user2, "Quería información sobre la oferta");
		Message m223 = new Message(user3, "Buenos días");
		Message m224 = new Message(user3, "Qué quiere saber?");
		messagesService.addMessage(m221, chat22);
		messagesService.addMessage(m222, chat22);
		messagesService.addMessage(m223, chat22);
		messagesService.addMessage(m224, chat22);
		Message m231 = new Message(user2, "Buenos días");
		Message m232 = new Message(user2, "Quería información sobre la oferta");
		Message m233 = new Message(user3, "Buenos días");
		Message m234 = new Message(user3, "Qué quiere saber?");
		messagesService.addMessage(m231, chat23);
		messagesService.addMessage(m232, chat23);
		messagesService.addMessage(m233, chat23);
		messagesService.addMessage(m234, chat23);

		Message m311 = new Message(user3, "Buenos días");
		Message m312 = new Message(user3, "Quería información sobre la oferta");
		Message m313 = new Message(user4, "Buenos días");
		Message m314 = new Message(user4, "Qué quiere saber?");
		messagesService.addMessage(m311, chat31);
		messagesService.addMessage(m312, chat31);
		messagesService.addMessage(m313, chat31);
		messagesService.addMessage(m314, chat31);
		Message m321 = new Message(user3, "Buenos días");
		Message m322 = new Message(user3, "Quería información sobre la oferta");
		Message m323 = new Message(user4, "Buenos días");
		Message m324 = new Message(user4, "Qué quiere saber?");
		messagesService.addMessage(m321, chat32);
		messagesService.addMessage(m322, chat32);
		messagesService.addMessage(m323, chat32);
		messagesService.addMessage(m324, chat32);
		Message m331 = new Message(user3, "Buenos días");
		Message m332 = new Message(user3, "Quería información sobre la oferta");
		Message m333 = new Message(user4, "Buenos días");
		Message m334 = new Message(user4, "Qué quiere saber?");
		messagesService.addMessage(m331, chat33);
		messagesService.addMessage(m332, chat33);
		messagesService.addMessage(m333, chat33);
		messagesService.addMessage(m334, chat33);

		Message m411 = new Message(user4, "Buenos días");
		Message m412 = new Message(user4, "Quería información sobre la oferta");
		Message m413 = new Message(user5, "Buenos días");
		Message m414 = new Message(user5, "Qué quiere saber?");
		messagesService.addMessage(m411, chat41);
		messagesService.addMessage(m412, chat41);
		messagesService.addMessage(m413, chat41);
		messagesService.addMessage(m414, chat41);
		Message m421 = new Message(user4, "Buenos días");
		Message m422 = new Message(user4, "Quería información sobre la oferta");
		Message m423 = new Message(user5, "Buenos días");
		Message m424 = new Message(user5, "Qué quiere saber?");
		messagesService.addMessage(m421, chat42);
		messagesService.addMessage(m422, chat42);
		messagesService.addMessage(m423, chat42);
		messagesService.addMessage(m424, chat42);
		Message m431 = new Message(user4, "Buenos días");
		Message m432 = new Message(user4, "Quería información sobre la oferta");
		Message m433 = new Message(user5, "Buenos días");
		Message m434 = new Message(user5, "Qué quiere saber?");
		messagesService.addMessage(m431, chat43);
		messagesService.addMessage(m432, chat43);
		messagesService.addMessage(m433, chat43);
		messagesService.addMessage(m434, chat43);

		Message m511 = new Message(user5, "Buenos días");
		Message m512 = new Message(user5, "Quería información sobre la oferta");
		Message m513 = new Message(user1, "Buenos días");
		Message m514 = new Message(user1, "Qué quiere saber?");
		messagesService.addMessage(m511, chat51);
		messagesService.addMessage(m512, chat51);
		messagesService.addMessage(m513, chat51);
		messagesService.addMessage(m514, chat51);
		Message m521 = new Message(user5, "Buenos días");
		Message m522 = new Message(user5, "Quería información sobre la oferta");
		Message m523 = new Message(user1, "Buenos días");
		Message m524 = new Message(user1, "Qué quiere saber?");
		messagesService.addMessage(m521, chat52);
		messagesService.addMessage(m522, chat52);
		messagesService.addMessage(m523, chat52);
		messagesService.addMessage(m524, chat52);
		Message m531 = new Message(user5, "Buenos días");
		Message m532 = new Message(user5, "Quería información sobre la oferta");
		Message m533 = new Message(user1, "Buenos días");
		Message m534 = new Message(user1, "Qué quiere saber?");
		messagesService.addMessage(m531, chat53);
		messagesService.addMessage(m532, chat53);
		messagesService.addMessage(m533, chat53);
		messagesService.addMessage(m534, chat53);
	}

}
