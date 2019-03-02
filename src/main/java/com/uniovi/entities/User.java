package com.uniovi.entities;

import java.util.*;
import javax.persistence.*;

import com.uniovi.entities.types.Rol;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String surname;

	@Column(unique = true)
	private String email;
	private String password;

	@Transient
	private String repassword;
	private double money;

	@Enumerated(EnumType.STRING)
	private Rol rol;
	private boolean active;

	@OneToMany(mappedBy = "owner")
	private Set<Sale> publishedSales = new HashSet<>();

	@OneToMany(mappedBy = "buyer")
	private Set<Sale> boughtSales = new HashSet<>();

	@OneToMany(mappedBy = "sender")
	private Set<Sale> sentSales = new HashSet<>();

	@OneToMany(mappedBy = "receiver")
	private Set<Sale> receivedSales = new HashSet<>();

	public User() {
		this.active = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Sale> getPublishedSales() {
		return publishedSales;
	}

	public void setPublishedSales(Set<Sale> publishedSales) {
		this.publishedSales = publishedSales;
	}

	public Set<Sale> getBoughtSales() {
		return boughtSales;
	}

	public void setBoughtSales(Set<Sale> boughtSales) {
		this.boughtSales = boughtSales;
	}

	public Set<Sale> getSentSales() {
		return sentSales;
	}

	public void setSentSales(Set<Sale> sentSales) {
		this.sentSales = sentSales;
	}

	public Set<Sale> getReceivedSales() {
		return receivedSales;
	}

	public void setReceivedSales(Set<Sale> receivedSales) {
		this.receivedSales = receivedSales;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
		return "User [id=" + id + ", name=" + name + ", surname=" + surname 
				+ ", email=" + email + ", password="
				+ password + ", repassword=" + repassword + ", money=" + money 
				+ ", active=" + active
				+ ", publishedSales=" + publishedSales + ", boughtSales=" + 
				boughtSales + ", sentSales=" + sentSales
				+ ", receivedSales=" + receivedSales + "]";
	}

}
