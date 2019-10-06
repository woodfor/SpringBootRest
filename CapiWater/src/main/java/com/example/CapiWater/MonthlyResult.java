package com.example.CapiWater;

public class MonthlyResult {

	String name;
	String area;
	long usage;
	int date;
	
	public MonthlyResult(String name, String area ,long usage, int date) {
		super();
		this.area = area;
		this.name = name;
		this.usage = usage;
		this.date = date;
	}
	
	public String getArea() {
		return area;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getUsage() {
		return usage;
	}

	public void setUsage(long usage) {
		this.usage = usage;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}
}
