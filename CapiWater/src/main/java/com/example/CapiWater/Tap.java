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
@Table(name = "tap")
public class Tap {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tid;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "btaddress", nullable = false)
	private String btaddress;
	
	@Column(name = "token", nullable = false)
	private String token;
	
	@OneToMany(mappedBy = "tap", cascade = CascadeType.ALL)
	private Set<Report> reports;
	
	@ManyToOne
    @JoinColumn
    private Area area;
	
	@OneToMany(mappedBy = "tap", cascade = CascadeType.ALL)
	private Set<Notification> notifications;

	public Tap(String name, String btaddress, Report... reports) {
		this.name = name;
		this.reports = Stream.of(reports).collect(Collectors.toSet());
	    this.reports.forEach(x -> x.setTap(this));
		
	}
	public Tap(String name, String btaddress, Area area, String token) {
		this.name = name;
		this.btaddress = btaddress;
		this.area = area;
		this.token = token;
		
	}
	
	public Tap() {
		
	}

	
	

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return this.token;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	public long getTid() {
		return tid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBtaddress() {
		return btaddress;
	}
	public Area fetchArea() {
		return area;
	}
	public void setBtaddress(String btaddress) {
		this.btaddress = btaddress;
	}
	
	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}
	
	
}
