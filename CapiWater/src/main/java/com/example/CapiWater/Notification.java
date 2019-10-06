package com.example.CapiWater;

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

@Entity
@Table(name = "notification")
public class Notification {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long nid;
	
	@Column(name = "duration", nullable = false)
	private int duration;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	@ManyToOne
    @JoinColumn
    private Tap tap;

	
	
	public Notification(int duration, Date dateTime, Tap tap) {
		super();
		this.duration = duration;
		this.dateTime = dateTime;
		this.tap = tap;
	}

	public Notification() {}
	
	public long getNid() {
		return nid;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	

	public String getAreaName() {
		return tap.fetchArea().getName();
	}

	public String getTapName() {
		return tap.getName();
	}

	public void setTap(Tap tap) {
		this.tap = tap;
	}
	

}
