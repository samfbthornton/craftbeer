package com.bae.craftbeer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@GetMapping("/getBeers")
	public ResponseEntity<List<CraftBeer>> getAllBeers() {
		System.out.println("Delicious Beers: " + this.service.getAllBeers());
		List<CraftBeer> getAll = this.service.getAllBeers();
		return new ResponseEntity<List<CraftBeer>>(getAll, HttpStatus.OK);
	}

	@GetMapping("/getBeerByID/{id}")
	public CraftBeer getBeer(@PathVariable int id) {
		return this.service.getBeerByID(id);
	}

	@GetMapping("/getBeerByName/{name}")
	public List<CraftBeer> getBeer(@PathVariable String name) {
		return this.service.getBeerByName(name);
	}

	@PostMapping("/createBeer")
	public ResponseEntity<CraftBeer> createCraftBeer(@RequestBody CraftBeer cb) {
		CraftBeer created = this.service.createCraftBeer(cb);
		return new ResponseEntity<>(created, HttpStatus.CREATED); // body, status code
	}

	@PutMapping("/replaceBeer/{id}")
	public ResponseEntity<CraftBeer> replaceCraftBeer(@PathVariable int id, @RequestBody CraftBeer cb) {
		CraftBeer updated = this.service.replaceCraftBeer(id, cb);
		return new ResponseEntity<CraftBeer>(updated, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteBeer/{id}")
	public ResponseEntity<String> deleteCraftBeer(@PathVariable int id) {
		String body = this.service.deleteCraftBeer(id);
		return new ResponseEntity<String>(body, HttpStatus.NO_CONTENT);
	}

}
