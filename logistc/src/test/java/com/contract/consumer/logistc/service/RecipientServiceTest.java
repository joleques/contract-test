package com.contract.consumer.logistc.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.contract.consumer.logistc.dto.Recipient;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.PactTestExecutionContext;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.ConsumerPactTest;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.pactfoundation.consumer.dsl.LambdaDsl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipientServiceTest extends ConsumerPactTest{

	@Autowired
	RecipientService recipientService;
	
	@Override
    @Pact(provider="ClientProvider", consumer="LogisticServiceBase")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
       
        DslPart body = LambdaDsl.newJsonBody((responseJson) -> responseJson
                .stringType("name", "Hatsune Miku")
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

    @Override
    protected String providerName() {
        return "ClientProvider";
    }

    @Override
    protected String consumerName() {
        return "LogisticServiceBase";
    }

    @Override
    protected void runTest(MockServer mockServer, PactTestExecutionContext context) {
    	recipientService.setRootUri(mockServer.getUrl());
    	Recipient recipient = recipientService.getRecipient("2");
        assertEquals(recipient.getName(), "Hatsune Miku");
    }
}
