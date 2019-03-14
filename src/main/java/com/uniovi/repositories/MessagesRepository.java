package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uniovi.entities.Message;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;

public interface MessagesRepository extends JpaRepository<Message, Long>{

	

}
