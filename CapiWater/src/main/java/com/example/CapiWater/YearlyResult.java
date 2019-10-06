package com.example.CapiWater;

public class YearlyResult {

	String name;
	String area;
	long usage;
	int month;
	
	public YearlyResult(String name,String area, long usage, int month) {
		super();
		this.area = area;
		this.name = name;
		this.usage = usage;
		this.month = month;
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

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

}
