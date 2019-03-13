package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("/sales/list")
	public String listSales(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("salesList", saleService.getSalesByUser(activeUser));
		return "sales/list";
	}
	
	@RequestMapping("/sales/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		saleService.deleteSale(id);
		return "redirect:/sales/list";
	}
	
	private Page<Sale> getPageSales(Pageable pageable, String searchText) {
		if (searchText != null && !searchText.isEmpty()) {
			return saleService.findToSellSearchText(pageable, searchText);
		}
		return saleService.findToSell(pageable);
	}

	@GetMapping("/sales/search")
	public String search(Model model, Pageable pageable, @RequestParam(required = false) String searchText) {
		Page<Sale> salePage = getPageSales(pageable, searchText);
		model.addAttribute("page", salePage);
		model.addAttribute("sales", salePage.getContent());
		return "sales/search";
	}
}