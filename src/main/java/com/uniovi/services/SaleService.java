package com.uniovi.services;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.entities.types.SaleStatus;
import com.uniovi.repositories.SaleRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private UsersRepository usersRepository;

	public void addSale(Sale sale, User activeUser) {
		sale.setOwner(activeUser);
		sale.setCreationDate(LocalDateTime.now());
		saleRepository.save(sale);
	}

	public List<Sale> getSalesByUser(User user) {
		return saleRepository.findAllByUser(user);
	}

	public void deleteSale(Long id) {
		saleRepository.deleteById(id);
	}

	public Page<Sale> findToSell(Pageable pageable) {
		return saleRepository.findToSell(pageable);
	}

    public Page<Sale> findToSellSearchText(Pageable pageable, String searchText) {
        return saleRepository.findToSellSearchText(pageable, searchText);
    }

	public Sale getSaleById(Long id) {
		return saleRepository.findById(id).get();
	}
	
	public Sale findById(Long id) {
		return saleRepository.getOne(id);
	}

	public boolean buy(Sale sale, User user) {
		if (sale.getPrice() <= user.getMoney()) {
			sale.setBuyer(user);
			sale.setStatus(SaleStatus.SOLD);
			saleRepository.save(sale);
			user.setMoney(user.getMoney() - sale.getPrice());
			usersRepository.save(user);
			return true;
		}
		return false;
	}
	
	public List<Sale> findByBuyerId(Long id) {
        return saleRepository.findByBuyerId(id);
    }
}
