package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;

public interface ConversationsRepository extends JpaRepository<Conversation, Long>{

	@Query("SELECT c FROM Conversation c WHERE (c.customer=?1 OR c.sale.owner=?1) AND c.sale=?2")
	Conversation getConversation(User activeUser, Sale sale);
	
}
