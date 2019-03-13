package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Message;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.repositories.MessagesRepository;

@Service
public class MessagesService  {

	@Autowired
	private MessagesRepository messagesRepository;
	
	public List<Message> getMessagesBySaleAndUser(User user, Sale sale){
		return messagesRepository.findAllByUserAndSale(user, sale);
	}

	public void addMessage(Message message, Sale sale, User sender) {
		// TODO Auto-generated method stub
		
	}

	public void addMessage(Message message) {
		messagesRepository.save(message);
	}

	
}
