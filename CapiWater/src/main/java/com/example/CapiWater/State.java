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
@Table(name = "state")
public class State {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sid;
	
	@Column(name = "name", nullable = false)
	private String name; 
	
	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
	private Set<Suburb> suburbs;
	
	public State(String name, Suburb...suburbs) {
		this.name = name;
		this.suburbs = Stream.of(suburbs).collect(Collectors.toSet());
		this.suburbs.forEach(x -> x.setState(this));
	}
	public State() {
		
	}
	public long getSid() {
		return sid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

	
}
