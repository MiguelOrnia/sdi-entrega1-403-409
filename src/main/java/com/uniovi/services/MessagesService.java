package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.repositories.ConversationsRepository;
import com.uniovi.repositories.MessagesRepository;

@Service
public class MessagesService  {

	@Autowired
	private MessagesRepository messagesRepository;
	
	@Autowired
	private ConversationsRepository conversationsRepository;


	public void addMessage(Message message) {
		messagesRepository.save(message);
	}

	public Conversation getConversation(Long id) {
		Conversation chat = conversationsRepository.findById(id).orElse(null);
		return chat;
	}
	
	public Conversation getConversation(User customer, Sale sale) {
		Conversation chat = conversationsRepository.getConversation(customer, sale);
		if(chat==null) {
			chat = new Conversation(customer, sale);
			conversationsRepository.save(chat);
		}
		return chat;
	}

	public void addMessage(Message message, Conversation chat) {
		chat.addMessage(message);
		message.setBelongs(chat);
		messagesRepository.save(message);
		conversationsRepository.save(chat);
	}

	public List<Conversation> getConversations(User activeUser) {
		List<Conversation> conversations = new ArrayList<Conversation>();
		conversations.addAll(activeUser.getParticipates());
		activeUser.getPublishedSales().forEach(s -> conversations.addAll(s.getConversations()));
		return conversations;
	}

	public void deleteConversation(Long id) {
		conversationsRepository.deleteById(id);
	}

	
}
