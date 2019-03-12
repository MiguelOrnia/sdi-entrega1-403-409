package com.uniovi.services;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.repositories.SaleRepository;

@Service
public class SaleService  {

	@Autowired
	private SaleRepository saleRepository;
	
	
	public void addSale(Sale sale, User activeUser) {
		sale.setOwner(activeUser);
		sale.setCreationDate(LocalDateTime.now());
		saleRepository.save(sale);
	}
	
	public List<Sale> getSalesByUser(User user){
		return saleRepository.findAllByUser(user);
	}
}
