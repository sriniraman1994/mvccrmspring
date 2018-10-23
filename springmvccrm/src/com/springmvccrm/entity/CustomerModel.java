package com.springmvccrm.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonView;
import com.springmvccrm.customvalidations.EmailCheck;
import com.springmvccrm.jsonviews.JViews;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="customer_tracker")
public class CustomerModel {
	public CustomerModel() {
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@JsonView(JViews.Data.class)
	private int id;
	@NotNull(message="isRequired")
	@Size(min=1,message="isrequired")
	@Column(name="first_name")
	@JsonView(JViews.Data.class)
	private String firstName;
	@NotNull(message="isRequired")
	@Column(name="last_name")
	@JsonView(JViews.Data.class)
	private String lastName;
	@NotNull(message="isRequired")
	@Email(message="provide proper email address")
	@Column(name="email")
	@EmailCheck(value="@gmail.com",message="Email already exists")
	@JsonView(JViews.Data.class)
	private String email;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
