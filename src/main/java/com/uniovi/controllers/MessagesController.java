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

import com.uniovi.entities.Conversation;
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
	public String getMessages(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		
		model.addAttribute("conversations", messagesService.getConversations(activeUser));
		return "messages/messages";
	}

	@RequestMapping("/messages/{convId}")
	public String getMessages(Model model, @PathVariable Long convId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);

		Conversation chat = messagesService.getConversation(convId);

		if (chat != null) {
			httpSession.setAttribute("currentChat", chat);
			List<Message> messages = chat.getMessages();
			model.addAttribute("messages", messages);
			model.addAttribute("activeUser", activeUser);
			return "messages/chat";
		}
		return "redirect:/messages";
	}
	
	@RequestMapping("/sales/message/{saleId}")
	public String getSaleMessage(Model model, @PathVariable Long saleId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		Sale sale = salesService.findById(saleId);
		if(sale!=null) {
			Conversation chat = messagesService.getConversation(activeUser, sale);
			httpSession.setAttribute("currentChat", chat);
			model.addAttribute("activeUser", activeUser);
			return "messages/chat";
		}
		
		return "redirect:/";
	}

	@RequestMapping(value = "/messages", method = RequestMethod.POST)
	public String getMessages(@ModelAttribute Message message) {
		Conversation chat = (Conversation) httpSession.getAttribute("currentChat");
		chat = messagesService.getConversation(chat.getId());
		if (chat != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String email = auth.getName();
			User sender = usersService.getUserByEmail(email);

			message.setSender(sender);
			message.setDate(LocalDateTime.now());
			messagesService.addMessage(message, chat);
			return "redirect:messages/" + chat.getId();
		}
		return "redirect:messages";
	}

}