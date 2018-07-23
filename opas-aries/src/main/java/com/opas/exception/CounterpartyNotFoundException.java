package com.opas.exception;

public class CounterpartyNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 2060820041230196335L;
	
	private String counterpartyId;

	   public CounterpartyNotFoundException(String counterpartyId) {
	      this.counterpartyId = counterpartyId;
	   }
	   
	   public String getCounterpartyId() {
	      return counterpartyId;
	   }

	}
