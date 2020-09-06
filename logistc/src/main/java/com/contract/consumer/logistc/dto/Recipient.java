package com.contract.consumer.logistc.dto;

public class Recipient {
	
	private String name;

	private String address;

	public Recipient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recipient(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
	
	
}
