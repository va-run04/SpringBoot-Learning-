package com.springrest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonProperty("full_name")
	private String name;
	
	
	private String city;
	
	@JsonIgnore
	private String email;
	
	public Student() {};

	public Student(Integer id, String name, String city, String email) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.email = email;
	}

	public Integer getId() {
	    return id;
	}
	public void setId(Integer id) {
	    this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", city=" + city + ", email=" + email + "]";
	}
	
	
	
	

}
