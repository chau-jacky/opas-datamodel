package com.opas.domain.repository;

import java.util.List;

import com.opas.domain.Counterparty;

public interface CounterpartyRepository {

	List<Counterparty> getAllCounterparties();

	Counterparty getCounterpartyById(String counterpartyID);
}
