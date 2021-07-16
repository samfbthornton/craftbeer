package com.bae.craftbeer.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.craftbeer.data.CraftBeer;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // try random ports until it finds a free one
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:craftbeer-schema.sql",
		"classpath:craftbeer-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class CraftBeerControllerIntegrationTest {

	@Autowired // tells Spring to inject the MockMVC object into this class
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper; // yanks the class Spring uses to convert java to JSON

	@Test
	void testCreate() throws Exception {
		CraftBeer testBeer = new CraftBeer("Iris Brew Co", "Pale Ale", 4.2, true);
		// convert to json
		String testBeerAsJSON = this.mapper.writeValueAsString(testBeer);

		System.out.println(testBeer);
		System.out.println(testBeerAsJSON);

		// body, method, address and the content-type header
		RequestBuilder request = post("/createBeer").contentType(MediaType.APPLICATION_JSON).content(testBeerAsJSON);

		// check the response body and status

		ResultMatcher checkStatus = status().isCreated();

		CraftBeer testCreatedBeer = new CraftBeer("Iris Brew Co", "Pale Ale", 4.2, true);
		testCreatedBeer.setId(2); // due to the AUTO_INCREMENT
		String testCreatedBeerAsJSON = this.mapper.writeValueAsString(testCreatedBeer);

		ResultMatcher checkBody = content().json(testCreatedBeerAsJSON);
// SEND request and check status + body
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDelete() throws Exception {
		RequestBuilder request = delete("/deleteBeer/1");

		ResultMatcher checkStatus = status().isNoContent();
		ResultMatcher checkBody = content().string("1 has been drunk. Soz.");

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetAll() throws Exception {
		RequestBuilder request = get("/getBeers");

		ResultMatcher checkStatus = status().isOk();

		// CraftBeer testCreatedKit = new CraftBeer("Iris Brew Co", "Pale Ale", 4.2,
		// true);

		List<CraftBeer> testCreatedList = new ArrayList<>();
		CraftBeer testGetBeer = new CraftBeer("Iris Brew Co", "Pale Ale", 4.2, true);
		testGetBeer.setId(1);
		testCreatedList.add(testGetBeer);
		// testCreatedList.containsAll(testCreatedList);
		String testListAsJSON = this.mapper.writeValueAsString(testCreatedList);

		ResultMatcher checkBody = content().json(testListAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetByID() throws Exception {
		RequestBuilder request = get("/getBeerByID/1");

		ResultMatcher checkStatus = status().isOk();

		CraftBeer testGetBeer = new CraftBeer("Iris Brew Co", "Pale Ale", 4.2, true);
		testGetBeer.setId(1);
		String testGetByIDAsJson = this.mapper.writeValueAsString(testGetBeer);

		ResultMatcher checkBody = content().json(testGetByIDAsJson);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test // Why doesn't this work?
	void testGetByName() throws Exception {
		List<CraftBeer> testCreatedList = new ArrayList<>();
		RequestBuilder request = get("/getBeerByName/Pale Ale");

		ResultMatcher checkStatus = status().isOk();

		CraftBeer testGetBeer = new CraftBeer("Iris Brew Co", "Pale Ale", 4.2, true);
		testGetBeer.setId(1);
		testCreatedList.add(testGetBeer);
		String testGetByNameAsJson = this.mapper.writeValueAsString(testCreatedList);

		ResultMatcher checkBody = content().json(testGetByNameAsJson);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testReplace() throws Exception {
		CraftBeer testBeer = new CraftBeer("Iris Brew Co", "Pale Ale", 4.2, true);
		String testBeerAsJSON = this.mapper.writeValueAsString(testBeer);

		System.out.println(testBeer);
		System.out.println(testBeerAsJSON);

		RequestBuilder request = put("/replaceBeer/1").contentType(MediaType.APPLICATION_JSON).content(testBeerAsJSON);

		CraftBeer testUpdatedBeer = new CraftBeer("Iris Brew Co", "Pale Ale", 4.2, true);
		testUpdatedBeer.setId(1);// due to the AUTO_INCREMENT
		String testUpdatedBeerAsJSON = this.mapper.writeValueAsString(testUpdatedBeer);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testUpdatedBeerAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

}
