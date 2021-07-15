package com.bae.craftbeer.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.craftbeer.data.CraftBeer;

@Repository
public interface CraftBeerRepo extends JpaRepository<CraftBeer, Integer> {

	List<CraftBeer> findByNameIgnoreCase(String name);

}
