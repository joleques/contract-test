package com.contract.consumer.logistc.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.contract.consumer.logistc.dto.Recipient;

@Service
public class RecipientService {

	private String rootUri = "http://locahost:8091";

	public RecipientService() {
	}

	public Recipient getRecipient(String id) {
		RestTemplate restTemplate = new RestTemplateBuilder().rootUri(rootUri).build();
		return restTemplate.getForObject("/client/" + id, Recipient.class);
	}

	public void setRootUri(String rootUri) {
		this.rootUri = rootUri;
	}
	
}
