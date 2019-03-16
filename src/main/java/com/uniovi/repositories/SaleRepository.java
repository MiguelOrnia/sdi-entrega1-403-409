package com.uniovi.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT s FROM Sale s WHERE s.owner = ?1 AND s.active = true "
			+ "ORDER BY s.id ASC ")
	List<Sale> findAllByUser(User user);

	@Query("SELECT s FROM Sale s WHERE (s.status ='ONSALE' OR "
			+ "s.status='HIGHLIGHTED') AND s.owner.id != ?1 AND s.active = true")
	Page<Sale> findToSell(Pageable pageable, Long id);

	@Query("SELECT s FROM Sale s WHERE s.title LIKE %?1% "
			+ "AND s.status = 'HIGHLIGHTED' " + "OR s.title LIKE %?1% "
			+ "AND s.status = 'ONSALE' AND s.active = true")
	Page<Sale> findToSellSearchText(Pageable pageable, String searchText);

	List<Sale> findByBuyerId(Long id);
}
