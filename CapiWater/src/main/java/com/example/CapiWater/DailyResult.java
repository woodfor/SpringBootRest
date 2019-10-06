package com.example.CapiWater;

public class DailyResult {
	String name;
	String area;
	long usage;
	int hour;
	
	public DailyResult(String name, String area, long usage, int hour) {
		super();
		this.area = area;
		this.name = name;
		this.usage = usage;
		this.hour = hour;
	}
	public DailyResult() {
		super();
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
	public void setUsage(int usage) {
		this.usage = usage;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	
}
