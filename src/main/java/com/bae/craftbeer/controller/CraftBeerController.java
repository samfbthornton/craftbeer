package com.bae.craftbeer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.craftbeer.data.CraftBeer;
import com.bae.craftbeer.service.CraftBeerService;

@RestController
public class CraftBeerController {

	private CraftBeerService service;

	public CraftBeerController(CraftBeerService service) {
		super();
		this.service = service;
	}

	@GetMapping("/getbeers")
	public List<CraftBeer> getAllBeers() {
		System.out.println("Delicious Beers: ");
		return this.service.getAllBeers();

	}

	@GetMapping("/getbeer/{id}")
	public CraftBeer getBeer(@PathVariable int id) {
		return this.service.getBeer(id);
	}

	@PostMapping("/createbeer")
	public void createCraftBeer(@RequestBody CraftBeer cb) {
		this.service.createCraftBeer(cb);
	}

	@DeleteMapping("/deleteCraftBeer/{id}")
	public void deleteCraftBeer(@PathVariable int id) {
		this.service.deleteCraftBeer(id);
	}

	@PutMapping("/replaceCraftBeer/{id}")
	public void replaceCraftBeer(@PathVariable int id, @RequestBody CraftBeer cb) {
		this.service.replaceCraftBeer(id, cb);
	}

}
