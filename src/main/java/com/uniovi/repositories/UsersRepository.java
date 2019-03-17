package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uniovi.entities.User;

public interface UsersRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findByName(String name);

	@Query("SELECT u FROM User u WHERE u.active = true "
			+ "AND u.role != 'ROLE_ADMIN'")
	List<User> findAllActive();
}
