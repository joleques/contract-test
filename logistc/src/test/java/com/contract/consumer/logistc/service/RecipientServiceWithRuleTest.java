package com.contract.consumer.logistc.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.contract.consumer.logistc.dto.Recipient;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.pactfoundation.consumer.dsl.LambdaDsl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipientServiceWithRuleTest {

	@Autowired
	RecipientService userServiceClient;
	
	@Rule
    public PactProviderRule mockProvider = new PactProviderRule("ClientProvider", this);
	
	@Pact(consumer = "logisticServiceRule")
	public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        DslPart body = LambdaDsl.newJsonBody((responseJson) -> responseJson
                .stringType("name", "José Maria")
                .stringType("address", "Rua 15 de Novembro 520")
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
    	userServiceClient.setRootUri(mockProvider.getUrl());
    	Recipient recipient = userServiceClient.getRecipient("2");
        assertEquals(recipient.getName(), "José Maria");
        assertEquals(recipient.getAddress(), "Rua 15 de Novembro 520");
    }
}
