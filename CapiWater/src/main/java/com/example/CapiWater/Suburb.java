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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "suburb")
public class Suburb {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long subid;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "postcode", nullable = false)
	private String postcode;
	
	@ManyToOne
    @JoinColumn
    private State state;
	
	@OneToMany(mappedBy = "suburb", cascade = CascadeType.ALL)
	private Set<House> houses;
	
	public Suburb(String name, String postcode, House... houses) {
		this.name = name;
		this.postcode = postcode;
		this.houses = Stream.of(houses).collect(Collectors.toSet());
	    this.houses.forEach(x -> x.setSuburb(this));
	}
	public Suburb(String name, String postcode) {
		super();
		this.name = name;
		this.postcode = postcode;
	}
	public Suburb() {
		
	}
	public long getSubid() {
		return subid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	


}
