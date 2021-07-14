package com.bae.craftbeer.service;

import java.util.List;

import com.bae.craftbeer.data.CraftBeer;

public interface CraftBeerService {

	public void createCraftBeer(CraftBeer cb);

	public List<CraftBeer> getAllBeers();

	public CraftBeer getBeer(int id);

	public String replaceCraftBeer(int id, CraftBeer newCraftBeer);

	public String deleteCraftBeer(int id);
}
