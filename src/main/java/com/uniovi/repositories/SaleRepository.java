package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;

public interface SaleRepository extends JpaRepository<Sale, Long>{

	 @Query("SELECT s FROM Sale s WHERE s.owner = ?1 ORDER BY s.id ASC ")
	 List<Sale> findAllByUser(User user);
	 
}
