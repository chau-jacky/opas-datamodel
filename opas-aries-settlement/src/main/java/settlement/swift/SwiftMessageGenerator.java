package settlement.swift;

import model.cashflow.CashFlow;
import model.cashflow.CashFlowSi;

public interface SwiftMessageGenerator {

	public Object generate(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
}
