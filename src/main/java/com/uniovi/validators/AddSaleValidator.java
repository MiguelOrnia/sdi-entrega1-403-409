package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;

@Component
public class AddSaleValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Sale sale = (Sale) target;
		if (sale.getTitle().equals(" ")) {
			errors.rejectValue("title", "Error.empty");
		}
		if (sale.getDetails().equals(" ")) {
			errors.rejectValue("details", "Error.empty");
		}
		if (String.valueOf(sale.getPrice()).equals(" ")) {
			errors.rejectValue("price", "Error.empty");
		}
		if (sale.getPrice() < 0.0) {
			errors.rejectValue("price", "Error.addSale.price.value");
		}
	}
}