package com.bae.craftbeer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.bae.craftbeer.data.CraftBeer;

public class CraftBeerServiceList implements CraftBeerService {

	private List<CraftBeer> craftbeers = new ArrayList<>();

	@Override
	public List<CraftBeer> getAllBeers() {
		System.out.println("Delicious Beers: ");
		return this.craftbeers;

	}

	@Override
	public CraftBeer getBeer(@PathVariable int id) {
		CraftBeer found = this.craftbeers.get(id);
		return found;
	}

	public String getBeerByID(@PathVariable int id) {
		// System.out.println(this.craftbeers.size());
		// System.out.println("Delicious Beer: " + id);
		return "Delicious beer:" + id;
	}

	@Override
	public void createCraftBeer(@RequestBody CraftBeer cb) {
		System.out.println(cb);
		this.craftbeers.add(cb);
	}

	@Override
	public String deleteCraftBeer(@PathVariable int id) {
		this.craftbeers.remove(id);
		return "We drank the beer with ID: " + id;

	}

	@Override
	public String replaceCraftBeer(@PathVariable int id, @RequestBody CraftBeer cb) {
		this.craftbeers.set(id, cb);
		return "Craft Beer " + id + " has been drunk, and replaced with " + cb.getName();
	}

}
