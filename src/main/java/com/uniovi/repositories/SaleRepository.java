package com.uniovi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uniovi.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{

}
