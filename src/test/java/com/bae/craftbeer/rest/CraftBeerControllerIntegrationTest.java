package com.bae.craftbeer.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.craftbeer.data.CraftBeer;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // try random ports until it finds a free one
@AutoConfigureMockMvc
public class CraftBeerControllerIntegrationTest {

	@Autowired // tells Spring to inject the MockMVC object into this class
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper; // yanks the class Spring uses to convert java to JSON

	@Test
	void testCreate() throws Exception {
		CraftBeer testKit = new CraftBeer("Iris Brew Co", "NEIPA", 8.2, true);
		// convert to json
		String testKitAsJSON = this.mapper.writeValueAsString(testKit);

		System.out.println(testKit);
		System.out.println(testKitAsJSON);

		// body, method, address and the content-type header
		RequestBuilder request = post("/createBeer").contentType(MediaType.APPLICATION_JSON).content(testKitAsJSON);

		// check the response body and status

		ResultMatcher checkStatus = status().isCreated();

		CraftBeer testCreatedKit = new CraftBeer("Iris Brew Co", "NEIPA", 8.2, true);
		testCreatedKit.setId(1); // due to the AUTO_INCREMENT
		String testCreatedKitAsJSON = this.mapper.writeValueAsString(testCreatedKit);

		ResultMatcher checkBody = content().json(testCreatedKitAsJSON);
// SEND request and check status + body
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
}
