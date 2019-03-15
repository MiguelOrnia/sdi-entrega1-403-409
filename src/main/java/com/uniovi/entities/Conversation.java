package com.uniovi.entities;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conversation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	private User customer;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="belongs",cascade=CascadeType.ALL)
	private Set<Message> messages = new HashSet<>();
	
	@ManyToOne
	private Sale sale;
	
	public Conversation() {}
	
	public Conversation(User customer, Sale sale) {
		this.customer = customer;
		this.sale = sale;
	}

	public List<Message> getMessages() {
		return messages.stream().sorted(Comparator.comparing(Message::getDate)).collect(Collectors.toList());
	}

	public void addMessage(Message message) {
		this.messages.add(message);
	}


	public Long getId() {
		return id;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

}
