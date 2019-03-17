package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.services.SaleService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddSaleValidator;

@Controller
public class SaleController {

	@Autowired
	private SaleService saleService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private AddSaleValidator addSaleValidator;

	@RequestMapping("/sales/add")
	public String addSale(Model model) {
		model.addAttribute("sale", new Sale());
		return "sales/add";
	}

	@RequestMapping(value = "/sales/add", method = RequestMethod.POST)
	public String addSale(Model model, @Validated Sale sale,
			BindingResult result) {
		addSaleValidator.validate(sale, result);
		if (result.hasErrors()) {
			return "sales/add";
		}
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		saleService.addSale(sale, activeUser);
		model.addAttribute("success", "");
		model.addAttribute("addedSaleTitle", sale.getTitle());
		return "sales/add";
	}


	@RequestMapping("/sales/list")
	public String listSales(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
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
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		if (searchText != null && !searchText.isEmpty()) {
			return saleService.findToSellSearchText(pageable, searchText);
		}
		return saleService.findToSell(pageable, activeUser.getId());
	}

	@GetMapping("/sales/search")
	public String search(Model model, Pageable pageable,
			@RequestParam(required = false) String searchText,
			@RequestParam(required = false) Long error) {
		Page<Sale> salePage = getPageSales(pageable, searchText);
		if (searchText == null) {
			searchText = "";
		}
		if (error != null) {
			model.addAttribute("error", error);
		}
		model.addAttribute("page", salePage);
		model.addAttribute("sales", salePage.getContent());
		model.addAttribute("searchText", searchText);
		return "sales/search";
	}

	@GetMapping("/sales/buy/{id}")
	public String buy(@PathVariable Long id, Principal principal) {
		User user = usersService.findByEmail(principal.getName());
		Sale sale = saleService.findById(id);
		if (saleService.buy(sale, user)) {
			return "redirect:/sales/search?success";
		}
		return "redirect:/sales/search?error=" + id;
	}

	@GetMapping("/sales/purchased")
	public String purchased(Model model, Principal principal) {
		User user = usersService.findByEmail(principal.getName());
		model.addAttribute("sales", saleService.findByBuyerId(user.getId()));
		return "sales/purchased";

	}
}