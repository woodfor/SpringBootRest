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
@Table(name = "area")
public class Area {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aid;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToOne
    @JoinColumn
    private House house;
	
	@OneToMany(mappedBy = "area", cascade = CascadeType.ALL)
	private Set<Report> reports;
	
	@OneToMany(mappedBy = "area", cascade = CascadeType.ALL)
	private Set<Tap> taps;
	
	

	public Area(String name, Report... reports) {
		this.name = name;
		this.reports = Stream.of(reports).collect(Collectors.toSet());
	    this.reports.forEach(x -> x.setArea(this));
		
	}
	
	public Area() {
		
	}
	
	public Set<Tap> getTaps() {
		return taps;
	}

	public void setTaps(Set<Tap> taps) {
		this.taps = taps;
	}
	
	

	public long getAid() {
		return aid;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setHouse(House house) {
		this.house = house;
	}



	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}
	
	
}
