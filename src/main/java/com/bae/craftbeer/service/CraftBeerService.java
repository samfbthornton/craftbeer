package com.bae.craftbeer.service;

import java.util.List;

import com.bae.craftbeer.data.CraftBeer;

public interface CraftBeerService {

	public CraftBeer createCraftBeer(CraftBeer cb);

	public List<CraftBeer> getAllBeers();

	public CraftBeer getBeerByID(int id);

	public CraftBeer replaceCraftBeer(int id, CraftBeer newCraftBeer);

	public String deleteCraftBeer(int id);

	List<CraftBeer> getBeerByName(String name);
}
