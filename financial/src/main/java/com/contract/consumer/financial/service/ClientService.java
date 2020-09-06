package com.contract.consumer.financial.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientService {
	
	private String rootUri = "http://locahost:8091";

	public ClientService() {
	}

	public ClientDTO getUser(String id) {
		RestTemplate restTemplate = new RestTemplateBuilder().rootUri(rootUri).build();
		return restTemplate.getForObject("/client/" + id, ClientDTO.class);
	}

	public void setRootUri(String rootUri) {
		this.rootUri = rootUri;
	}
}
