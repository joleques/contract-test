package com.contract.producer.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contract.producer.client.dto.Client;

@RestController
@RequestMapping("/client")
public class ClientController {

	
	@GetMapping("/{id}")
	public Client getById(@PathVariable String id) {
		return new Client("José Maria", "94168764089", "Rua 15 de Novembro 520");
	}
	
}
