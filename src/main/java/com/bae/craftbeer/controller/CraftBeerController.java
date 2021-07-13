package com.bae.craftbeer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CraftBeerController {

	@GetMapping("/")
	public String hello() {
		return "Hello, World!";

	}

}