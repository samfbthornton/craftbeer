package com.bae.craftbeer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.craftbeer.data.CraftBeer;

@Service // FUTURE FUNCTIONALITY - PLZ DO NOT USE
public class CraftBeerServiceDB implements CraftBeerService {

	@Override
	public void createCraftBeer(CraftBeer cb) { // less fancy

	}

	@Override
	public List<CraftBeer> getAllBeers() {
		return null;
	}

	@Override
	public CraftBeer getBeer(int id) {
		return null;
	}

	@Override
	public String replaceCraftBeer(int id, CraftBeer newCraftBeer) {
		return null;
	}

	@Override
	public String deleteCraftBeer(int id) {
		return null;
	}

}
