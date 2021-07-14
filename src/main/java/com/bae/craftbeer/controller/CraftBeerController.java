package com.bae.craftbeer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.craftbeer.data.CraftBeer;

@RestController
public class CraftBeerController {

	private List<CraftBeer> craftbeers = new ArrayList<>();

	public void addBeer(CraftBeer cb) {
		this.craftbeers.add(cb);
	}

	public void removeBeer(CraftBeer cb) {
		this.craftbeers.remove(cb);
	}

	@GetMapping("/getbeers")
	public List<CraftBeer> getBeers() {
		System.out.println("Delicious Beers: ");
		return this.craftbeers;

	}

	@GetMapping("/getbeer/{id}")
	public CraftBeer getBeer(@PathVariable int id) {
		CraftBeer found = this.craftbeers.get(id);
		return found;
	}

	@GetMapping("/getbeerbyid/{ID}")
	public String getBeerByID(@PathVariable int id) {
		// System.out.println(this.craftbeers.size());
		// System.out.println("Delicious Beer: " + id);
		return "Delicious beer:" + id;
	}

	@PostMapping("/addbeers")
	public void createCraftBeer(@RequestBody CraftBeer cb) {
		System.out.println(cb);
		this.craftbeers.add(cb);
	}

	@DeleteMapping("/deleteCraftBeer/{id}")
	public String deleteCraftBeer(@PathVariable int id) {
		this.craftbeers.remove(id);
		return "Deleted Beer at index: " + id;

	}

	@PutMapping("/replaceCraftBeer/{id}")
	public String replaceCraftBeer(@PathVariable int id, @RequestBody CraftBeer cb) {
		this.craftbeers.set(id, cb);
		return "Craft Beer " + id + " has been drunk, and replaced with " + cb.getName();
	}

}
