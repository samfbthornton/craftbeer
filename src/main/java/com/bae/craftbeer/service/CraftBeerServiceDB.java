package com.bae.craftbeer.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bae.craftbeer.data.CraftBeer;
import com.bae.craftbeer.data.repo.CraftBeerRepo;

@Primary
@Service // FUTURE FUNCTIONALITY - PLZ DO NOT USE
public class CraftBeerServiceDB implements CraftBeerService {

	private CraftBeerRepo repo;

	public CraftBeerServiceDB(CraftBeerRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public CraftBeer createCraftBeer(CraftBeer cb) { // less fancy
		return this.repo.save(cb);
	}

	@Override
	public List<CraftBeer> getAllBeers() {
		return this.repo.findAll();
	}

	@Override
	public CraftBeer getBeerByID(int id) {
		return this.repo.findById(id).get();
	}

	@Override
	public CraftBeer replaceCraftBeer(int id, CraftBeer newCB) {
		CraftBeer found = this.repo.findById(id).get();

		found.setAbv(newCB.getAbv());
		found.setBrewery(newCB.getBrewery());
		found.setName(newCB.getName());
		found.setNice(newCB.isNice());

		CraftBeer updated = this.repo.save(found);

		return updated;
	}

	@Override
	public String deleteCraftBeer(int id) {
		this.repo.deleteById(id);

		return id + " has been drunk. Soz.";
	}

	@Override
	public List<CraftBeer> getBeerByName(String name) {
		// TODO Auto-generated method stub
		return this.repo.findByNameIgnoreCase(name);
	}

}
