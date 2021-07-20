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
	void testDeleteSucceeded() {
		int id = 1;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.deleteCraftBeer(id)).isEqualTo(id + " has been drunk. Soz.");

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}

	@Test
	void testDeleteFails() {
		int id = 1;

		Mockito.when(this.repo.existsById(id)).thenReturn(true);

		assertThat(this.service.deleteCraftBeer(id)).isEqualTo("You're lucky! " + id + " hasn't been drunk yet!");

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}

	@Test
	void testCreate() {

		CraftBeer testCraftBeer = new CraftBeer("Iris Brew Co", "Pale Ale", 4.2, true);
		CraftBeer savedCraftBeer = new CraftBeer(1, "Iris Brew Co", "Pale Ale", 4.2, true);

		Mockito.when(this.repo.save(testCraftBeer)).thenReturn(savedCraftBeer);

		assertThat(this.repo.save(testCraftBeer)).isEqualTo(savedCraftBeer);

		Mockito.verify(this.repo, Mockito.times(1)).save(testCraftBeer);
	}

	@Test
	void testGetBeerByID() {
		int id = 1;
		CraftBeer testCraftBeer = new CraftBeer(id, "Iris Brew Co", "Pale Ale", 4.2, true);

		Mockito.when(this.repo.findById(testCraftBeer.getId())).thenReturn(Optional.of(testCraftBeer));

		assertThat(this.service.getBeerByID(id)).isEqualTo(testCraftBeer);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	void testGetBeerByName() {

		List<CraftBeer> testList = new ArrayList<>();
		CraftBeer testCraftBeer = new CraftBeer(1, "Iris Brew Co", "Pale Ale", 4.2, true);
		testList.add(testCraftBeer);

		String search = "pale ale";

		Mockito.when(this.repo.findByNameIgnoreCase(search)).thenReturn(testList);

		assertThat(this.service.getBeerByName(search)).isEqualTo(testList);

		Mockito.verify(this.repo, Mockito.times(1)).findByNameIgnoreCase(search);
	}

	@Test
	void testGetAllBeers() {
		int id = 1;
		List<CraftBeer> testList = new ArrayList<>();
		CraftBeer testCraftBeer = new CraftBeer(id, "Iris Brew Co", "Pale Ale", 4.2, true);
		testList.add(testCraftBeer);

		Mockito.when(this.repo.findAll()).thenReturn(testList);

		assertThat(this.service.getAllBeers()).isEqualTo(testList);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

}
