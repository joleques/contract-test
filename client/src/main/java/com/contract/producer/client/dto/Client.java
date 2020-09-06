package com.contract.producer.client.dto;

public class Client {

	private String name;
	private String cpf;
	private String address;


	public Client(String name, String cpf, String address) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.address = address;
	}


	public String getName() {
		return name;
	}


	public String getCpf() {
		return cpf;
	}


	public String getAddress() {
		return address;
	}
	
	
	
}
