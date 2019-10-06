package com.example.CapiWater;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Token")
public class Token {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tid;
	
	@Column(name = "token", nullable = false)
	private String token;
	
	public Token(String token) {
		this.token = token;
	}
	
	public Token() {
		
	}
	
	public void setID(long id) {
		this.tid = id;
	}
	
	public String getToken() {
		return token;
	}
	public long getID() {
		return tid;
	}
	public void setToken(String token) {
		this.token = token;
	}
}

