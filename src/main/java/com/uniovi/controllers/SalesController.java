package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.services.SaleService;
import com.uniovi.services.UsersService;

@Controller
public class SalesController {
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/sales/add")
	public String addSale() {
		return "sales/add";
		
	}
	
	@RequestMapping(value = "/sales/add", method = RequestMethod.POST)
	public String addSale(Model model, @ModelAttribute Sale sale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		saleService.addSale(sale,activeUser);
		model.addAttribute("success", "");
		model.addAttribute("addedSaleTitle", sale.getTitle());
		return "sales/add";
		
	}
}