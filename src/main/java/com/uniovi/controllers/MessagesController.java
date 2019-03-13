package com.uniovi.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Message;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.services.MessagesService;
import com.uniovi.services.SaleService;
import com.uniovi.services.UsersService;

@Controller
public class MessagesController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private MessagesService messagesService;

	@Autowired
	private SaleService salesService;
	
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping("/messages")
	public String getMessages() {
		
		return "messages/messages";
	}

	@RequestMapping("/messages/{saleId}")
	public String getMessages(Model model, @PathVariable(required = false) Long saleId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		if (saleId != null) {
			Sale sale = salesService.getSaleById(saleId);
			List<Message> messageList = messagesService.getMessagesBySaleAndUser(activeUser, sale);
			model.addAttribute("currentChat", messageList);
			model.addAttribute("activeUser", activeUser);
			httpSession.setAttribute("sale", sale);
		}
		return "messages/chat";
	}

	@RequestMapping(value = "/messages", method = RequestMethod.POST)
	public String getMessages(@ModelAttribute Message message) {
		Sale sale = (Sale) httpSession.getAttribute("sale");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User sender = usersService.getUserByEmail(email);
		message.setSale(sale);
		message.setSender(sender);
		message.setDate(LocalDateTime.now());
		messagesService.addMessage(message);
		return "redirect:messages/"+sale.getId();
	}

}