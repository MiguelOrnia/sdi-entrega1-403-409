package com.uniovi.entities;

import java.util.Set;

import javax.persistence.*;
import org.joda.time.LocalDateTime;

import com.uniovi.entities.types.SaleStatus;

@Entity
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String details;
	private double price;
	private LocalDateTime creationDate;
	
	@Enumerated(EnumType.STRING)
	private SaleStatus status;
	
	@ManyToOne
	private User owner;
	
	@ManyToOne
	private User buyer;
	
	@OneToMany(mappedBy="sale")
	private Set<Conversation> conversations;
	
	public Sale() {
		this.status = SaleStatus.ONSALE;
	}

	public Sale(String title, String details, double price) {
		super();
		this.title = title;
		this.details = details;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public SaleStatus getStatus() {
		return status;
	}

	public void setStatus(SaleStatus status) {
		this.status = status;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", title=" + title + ", details=" + details 
				+ ", price=" + price + ", creationDate="
				+ creationDate + ", owner=" + owner + ", buyer=" + buyer + ", "
						+ "]";
	}

	public Set<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(Set<Conversation> conversations) {
		this.conversations = conversations;
	}
	
		
}
