package com.bae.craftbeer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.craftbeer.data.CraftBeer;

@Service
public class CraftBeerServiceList implements CraftBeerService {

	private List<CraftBeer> craftbeers = new ArrayList<>();

	@Override
	public List<CraftBeer> getAllBeers() {
		System.out.println("Delicious Beers: ");
		return this.craftbeers;

	}

	@Override
	public CraftBeer getBeerByID(int id) {
		CraftBeer found = this.craftbeers.get(id);
		return found;
	}

	@Override
	public List<CraftBeer> getBeerByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

//	public String getBeerByID(int id) {
	// System.out.println(this.craftbeers.size());
	// System.out.println("Delicious Beer: " + id);
//		return "Delicious beer:" + id;
//	}

	@Override
	public CraftBeer createCraftBeer(CraftBeer cb) {
		System.out.println(cb);
		this.craftbeers.add(cb);
		return this.createCraftBeer(cb);
	}

	@Override
	public String deleteCraftBeer(int id) {
		this.craftbeers.remove(id);
		return "We drank the beer with ID: " + id;

	}

	@Override
	public String replaceCraftBeer(int id, CraftBeer cb) {
		this.craftbeers.set(id, cb);
		return "Craft Beer " + id + " has been drunk, and replaced with " + cb.getName();
	}

}
