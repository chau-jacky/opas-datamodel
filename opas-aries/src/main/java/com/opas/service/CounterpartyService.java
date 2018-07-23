package com.opas.service;

import java.util.List;

import com.opas.domain.Counterparty;

public interface CounterpartyService {

	List<Counterparty> getAllCounterparties();
	
	Counterparty getCounterpartyById(String counterpartyID);
}
