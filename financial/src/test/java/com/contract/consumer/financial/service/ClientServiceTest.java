package com.contract.consumer.financial.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.pactfoundation.consumer.dsl.LambdaDsl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

	@Autowired
	private ClientService service;
	
	@Rule
	public PactProviderRule pactProviderRule = new PactProviderRule("ClientProvider", this);
	
	@Pact(consumer = "FinacialServiceRule")
	public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        DslPart body = LambdaDsl.newJsonBody((responseJson) -> responseJson
                .stringType("name", "José Maria")
                .stringType("cpf", "94168764089")
               ).build();
        
        return builder
                .given("")
                .uponReceiving("Pact JVM example Pact interaction")
                .path("/client/2")
                .method("GET")
                .willRespondWith()
                .headers(headers)
                .status(200)
                .body(body)
                .toPact();
    }

    @Test
    @PactVerification(fragment = "createPact")
    public void runTest() {
    	service.setRootUri(pactProviderRule.getUrl());
    	ClientDTO client = service.getUser("2");
        assertEquals(client.getName(), "José Maria");
        assertEquals(client.getCpf(), "94168764089");
    }
}
