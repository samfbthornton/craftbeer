package com.bae.craftbeer.data;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
// tells spring that this class represents a table in the db
public class CraftBeer {

	@Id // Primary ID
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private int id;

	private String brewery;
	@Column(name = "beerName", unique = true)
	private String name;

	private double abv;
	private boolean nice;

	public CraftBeer() {
		// REQUIRED
	}

	public CraftBeer(String brewery, String name, double abv, boolean nice) {
		super();

		this.brewery = brewery;
		this.name = name;
		this.abv = abv;
		this.nice = nice;
	}

	public CraftBeer(int id, String brewery, String name, double abv, boolean nice) {
		super();
		this.id = id;
		this.brewery = brewery;
		this.name = name;
		this.abv = abv;
		this.nice = nice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(abv, brewery, id, name, nice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CraftBeer other = (CraftBeer) obj;
		return Double.doubleToLongBits(abv) == Double.doubleToLongBits(other.abv)
				&& Objects.equals(brewery, other.brewery) && id == other.id && Objects.equals(name, other.name)
				&& nice == other.nice;
	}

	// REQUIRED
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
