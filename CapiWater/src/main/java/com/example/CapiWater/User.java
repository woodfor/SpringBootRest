package com.example.CapiWater;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;
	
	@Column(name = "uuid", nullable = false,unique = true)
	private String uuid; 
	
	@Column(name = "name", nullable = false)
	private String name; 
	
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<House> houses;
	
	public User(String uuid, String name, House...houses) {
		this.name = name;
		this.uuid = uuid;
	
		this.houses = Stream.of(houses).collect(Collectors.toSet());
	    this.houses.forEach(x -> x.setUser(this));
	}
	
	public User(String uuid, String name) {
		this.name = name;
		this.uuid = uuid;
		
		
	}
	public User() {
		
	}
	
	public String getUUID() {
		return this.uuid;
	}
	public long getUID() {
		return this.uid;
	}
	public String getName() {
		return this.name;
	}
	
}
