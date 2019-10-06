package com.example.CapiWater;

public class RankResult {

	private long totalLiter;
	private String houseName;
	
	public RankResult( String houseName, long totalLiter) {
		super();
		this.totalLiter = totalLiter;
		this.houseName = houseName;
	}
	public long getTotalLiter() {
		return totalLiter;
	}
	public void setTotalLiter(long totalLiter) {
		this.totalLiter = totalLiter;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	
}
