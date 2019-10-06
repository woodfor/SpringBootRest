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
@Table(name = "house")
public class House {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hid;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	
	@Column(name = "address", nullable = true)
	private String address;
	
	@Column(name = "nop", nullable = false)
	private int nop;
	
	@ManyToOne
    @JoinColumn
    private User user;
	
	@ManyToOne
    @JoinColumn
    private Suburb suburb;
	
	@OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
	private Set<Area> areas;
	
	public House(String name, String address, Area... areas) {
		this.name = name;
		this.address = address;
		this.areas = Stream.of(areas).collect(Collectors.toSet());
	    this.areas.forEach(x -> x.setHouse(this));
	}
	
	public House(String name, String address, int nop) {
		this.name = name;
		this.address = address;
		
		this.nop = nop;
	}
	
	public House() {
		
	}

	public long getHid() {
		return hid;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Suburb getSuburb() {
		return suburb;
	}

	public void setSuburb(Suburb suburb) {
		this.suburb = suburb;
	}

	
	public Set<Area> getAreas() { return areas; }
	  
	public void setAreas(Set<Area> areas) { this.areas = areas; }
	 
	public int getNop() {
		return nop;
	}

	public void setNop(int nop) {
		this.nop = nop;
	}

	
}
