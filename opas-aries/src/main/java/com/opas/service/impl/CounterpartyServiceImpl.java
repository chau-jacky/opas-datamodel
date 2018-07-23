package com.opas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opas.domain.Counterparty;
import com.opas.domain.repository.CounterpartyRepository;
import com.opas.service.CounterpartyService;

@Service
public class CounterpartyServiceImpl implements CounterpartyService {

   @Autowired
   private CounterpartyRepository counterpartyRepository;

	@Override
	public List<Counterparty> getAllCounterparties() {
        return counterpartyRepository.getAllCounterparties();
	}

	@Override
	public Counterparty getCounterpartyById(String counterpartyID) {
	      return counterpartyRepository.getCounterpartyById(counterpartyID);
	}

}
