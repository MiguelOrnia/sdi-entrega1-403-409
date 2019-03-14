package com.uniovi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.uniovi.entities.*;
import com.uniovi.entities.types.Role;
import com.uniovi.services.SaleService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private SaleService saleService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SignUpFormValidator signUpFormValidator;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(Role.ROLE_STAND);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getRepassword());
		return "redirect:home";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		if (activeUser.isActive()) {
			model.addAttribute("money", activeUser.getMoney());
			if (activeUser.getRole().equals(Role.ROLE_ADMIN)) {
				return "homeAdmin";
			}
			model.addAttribute("salesList", saleService.getSalesByUser(activeUser));
			return "homeStandard";
		} 
		SecurityContextHolder.getContext().setAuthentication(null);
		return "redirect:login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, @RequestParam(required=false) String error) {
		if (error!=null) {
			model.addAttribute("error", error);
		}
		return "login";
	}

	@RequestMapping("/user/list")
	public String getListado(Model model) {
		model.addAttribute("usersList", usersService.getValidUsers());
		return "user/list";
	}
	
	@PostMapping("/user/delete")
	public String delete(@RequestParam(value = "ck", required = false) 
		List<Long> values) {
		if(values != null) {
			for (Long value : values) {
				usersService.deleteUser(value);
			}
			return "redirect:/user/list?success";
		} 
		return "redirect:/user/list?error";
	}
}