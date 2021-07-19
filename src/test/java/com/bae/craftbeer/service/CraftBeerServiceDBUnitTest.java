package com.bae.craftbeer.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.craftbeer.data.CraftBeer;
import com.bae.craftbeer.data.repo.CraftBeerRepo;

@SpringBootTest
@ActiveProfiles("test")
public class CraftBeerServiceDBUnitTest {

	@Autowired
	private CraftBeerServiceDB service;

	@MockBean
	private CraftBeerRepo repo;

	@Test
	void testUpdate() {
		int id = 1;

		CraftBeer testCraftBeer = new CraftBeer(id, "Iris Brew Co", "Pale Ale", 4.2, true);
		CraftBeer testNewCraftBeer = new CraftBeer(id, "Big Drop", "Stout", 0.5, false);

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(testCraftBeer));
		Mockito.when(this.repo.save(new CraftBeer(id, "Big Drop", "Stout", 0.5, false))).thenReturn(testNewCraftBeer);

		CraftBeer actual = this.service.replaceCraftBeer(id, testNewCraftBeer);

		assertThat(actual).isEqualTo(testNewCraftBeer);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(new CraftBeer(id, "Big Drop", "Stout", 0.5, false));

	}

	@Test
	void testDelete() {
		int id = 1;

		assertThat(this.service.deleteCraftBeer(id)).isEqualTo(id + " has been drunk. Soz.");
	}

	@Test
	void testCreate() {

		CraftBeer testCraftBeer = new CraftBeer(1, "Iris Brew Co", "Pale Ale", 4.2, true);

		Mockito.when(this.repo.save(new CraftBeer(1, "Iris Brew Co", "Pale Ale", 4.2, true))).thenReturn(testCraftBeer);

		assertThat(this.service.createCraftBeer(new CraftBeer(1, "Iris Brew Co", "Pale Ale", 4.2, true)))
				.isEqualTo(testCraftBeer);

		Mockito.verify(this.repo, Mockito.times(1)).save(new CraftBeer(1, "Iris Brew Co", "Pale Ale", 4.2, true));
	}

	@Test
	void testGetBeerByID() {
		int id = 1;
		CraftBeer testCraftBeer = new CraftBeer(id, "Iris Brew Co", "Pale Ale", 4.2, true);

		Mockito.when(this.repo.findById(testCraftBeer.getId())).thenReturn(Optional.of(testCraftBeer));

		assertThat(this.service.getBeerByID(id)).isEqualTo(testCraftBeer);
	}

	@Test
	void testGetBeerByName() {
		int id = 1;
		List<CraftBeer> testList = new ArrayList<>();
		CraftBeer testCraftBeer = new CraftBeer(id, "Iris Brew Co", "Pale Ale", 4.2, true);
		testList.add(testCraftBeer);

		Mockito.when(this.repo.findByNameIgnoreCase("Iris Brew Co")).thenReturn(testList);

		assertThat(this.service.getBeerByName("Iris Brew Co")).isEqualTo(testList);
	}

	@Test
	void testGetAllBeers() {
		int id = 1;
		List<CraftBeer> testList = new ArrayList<>();
		CraftBeer testCraftBeer = new CraftBeer(id, "Iris Brew Co", "Pale Ale", 4.2, true);
		testList.add(testCraftBeer);

		Mockito.when(this.repo.findAll()).thenReturn(testList);

		assertThat(this.service.getAllBeers()).isEqualTo(testList);
	}

}