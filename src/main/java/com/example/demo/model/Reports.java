package com.example.demo.model;

public class Reports 
{
	private String state;
	private String country;
	private int totalDeaths;
	private int diffFromPrevDay;
	
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDifferFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getTotalDeaths() {
		return totalDeaths;
	}
	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}
	@Override
	public String toString() {
		return "LocationStates [state=" + state + ", country=" + country + ", totalDeaths=" + totalDeaths
				+ "]";
	};

}
