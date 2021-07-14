package com.bae.craftbeer.data;

public class CraftBeer {

	private String brewery;
	private String name;

	private double abv;
	private boolean nice;

	public CraftBeer() {
	}

	public CraftBeer(String brewery, String name, double abv, boolean nice) {
		super();
		this.brewery = brewery;
		this.name = name;
		this.abv = abv;
		this.nice = nice;
	}

	public String getBrewery() {
		return brewery;
	}

	public void setBrewery(String brewery) {
		this.brewery = brewery;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAbv() {
		return abv;
	}

	public void setAbv(double abv) {
		this.abv = abv;
	}

	public boolean isNice() {
		return nice;
	}

	public void setNice(boolean nice) {
		this.nice = nice;
	}

	@Override
	public String toString() {
		return "Beer [ Brewery = " + brewery + ", Name = " + name + ", ABV = " + abv + ", Is it nice? " + nice + " ]";
	}

}
