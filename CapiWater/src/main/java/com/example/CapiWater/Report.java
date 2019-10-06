package com.example.CapiWater;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "report")
public class Report {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rid;
	
	@Column(name = "waterusage")
	private int waterUsage;
	
	@Column(name = "waterduration")
	private int waterDuration;
	
	@Temporal(TemporalType.TIME)
	private Date time;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
    @JoinColumn
    private Tap tap;
	
	@ManyToOne
    @JoinColumn
    private Area area;
	
	
	public Report( Area area,Tap tap, Date date,int usage, int duration) {
		this.waterUsage = usage;
		this.waterDuration = duration;
		this.time = date;
		this.date = date;
		this.tap = tap;
		this.area = area;
	}
	
	public Report() {
		
	}

	public long getRid() {
		return rid;
	}


	public int getWaterUsage() {
		return waterUsage;
	}

	public void setWaterUsage(int waterUsage) {
		this.waterUsage = waterUsage;
	}

	public int getWaterDuration() {
		return waterDuration;
	}

	public void setWaterDuration(int waterDuration) {
		this.waterDuration = waterDuration;
	}

	
	public Date getTime() {
		return time;
	}

	
	public void setTime(Date time) {
		this.time = time;
	}

	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public String getTapName() {
		return tap.getName();
	}

	public void setTap(Tap tap) {
		this.tap = tap;
	}

	public String getAreaName() {
		return area.getName();
	}

	public void setArea(Area area) {
		this.area = area;
	}
	

	
}
